package com.datn.service.impl;

import com.datn.dto.admin.order.change_status.ChangeStatusDto;
import com.datn.dto.admin.order.search.ListOrderRequest;
import com.datn.dto.customer.cart.response.CartResponse;
import com.datn.dto.customer.order.CancelOrder;
import com.datn.dto.customer.order.OrderRequest;
import com.datn.dto.customer.order.OrderSearch;
import com.datn.dto.customer.order.order_detail.ListOrderRes;
import com.datn.dto.customer.order.order_detail.OrderResponse;
import com.datn.dto.customer.order.order_detail.ProductOrderDetail;
import com.datn.entity.AddressEntity;
import com.datn.entity.OrderDetailEntity;
import com.datn.entity.OrderEntity;
import com.datn.entity.OrderStatusEntity;
import com.datn.entity.ProductOptionEntity;
import com.datn.service.OrderService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.DateUtils;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.MoneyUtils;
import com.datn.utils.common.PageableUtils;
import com.datn.utils.common.StringUtils;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyConst;
import com.datn.utils.constants.PuddyException;
import com.datn.utils.constants.enums.PaymentEnums;
import com.datn.utils.constants.enums.RoleEnum;
import com.datn.utils.constants.enums.StatusEnum;
import com.datn.utils.validator.auth.AuthValidator;
import com.datn.utils.validator.customer.order.CancelOrderValidator;
import com.datn.utils.validator.customer.order.CheckoutValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private static final String COMMA = ", ";
    private final PuddyRepository repository;

    @Override
    @Transactional
    public Object checkout(CurrentUser currentUser, OrderRequest req) {
        log.info("----- OrderService create order start with payload: {}", JsonUtils.toJson(req));
        AuthValidator.checkCustomer(currentUser);
        CheckoutValidator.validCheckout(req);

        AddressEntity address = repository.addressRepository.findById(req.getAddressId()).orElseThrow(() ->
            new PuddyException(PuddyCode.ADDRESS_NOT_FOUND)
        );

        List<CartResponse> cart = repository.cartRepository.getListCart(currentUser.getId());

        if (cart.isEmpty()) {

            throw new PuddyException(PuddyCode.CART_NOT_FOUND);
        }

//        DiscountEntity discount = null;
//        if (!StringUtils.isNullOrEmpty(req.getDiscountCode())) {
//            discount = repository.discountRepository.findByCode(req.getDiscountCode());
////            this.validDiscount(discount, cart, currentUser.getId(), req);
//        }
        Long total = this.getTotal( cart, req);

        String orderCode = "#".concat(UUID.randomUUID().toString());
        PaymentEnums payment = PaymentEnums.from(req.getPaymentMethod());
        OrderEntity order = OrderEntity.builder()
                .id(req.getId())
                .addressId(address.getId())
                .userId(currentUser.getId())
                .note(req.getNote())
                .payed(Boolean.FALSE)
                .payment(req.getPaymentMethod())
                .shipPrice(Long.valueOf(req.getShipPrice()))
                .code(orderCode)
                .status(StatusEnum.PENDING.name())
                .createdDate(new Date())
                .createdBy(currentUser.getCombinationName())
                .shopTotal(req.getShopTotal())
                .total(total)
                .discountId(1 + "")
                .build();

        log.info("----- OrderServiceImpl create before save: {}", JsonUtils.toJson(order));
        repository.orderRepository.save(order);
        log.info("----- OrderServiceImpl create before save: {}", JsonUtils.toJson(order));

        OrderStatusEntity orderStatus = OrderStatusEntity.builder()
                .id(UUID.randomUUID().toString())
                .orderId(order.getId())
                .status(StatusEnum.PENDING)
                .createdDate(new Date())
                .createdBy(currentUser.getId())
                .build();
        repository.orderStatusRepository.save(orderStatus);

        //update số lượng sản phảm sau khi đã update
        cart.stream().map(item -> {
            ProductOptionEntity productOption = repository.productOptionRepository.findById(item.getProductOptionId()).orElseThrow(() ->
                 new PuddyException(PuddyCode.PRODUCT_OPTION_NOT_FOUND));

            productOption.setQty(productOption.getQty() - item.getQuantity());

            return repository.productOptionRepository.save(productOption);

        }).collect(Collectors.toList());

        //save order to orderDetail
        cart.stream().map(item -> {
            OrderDetailEntity orderDetail = OrderDetailEntity.builder()
                    .id(UidUtils.generateUid())
                    .orderId(order.getId())
                    .productOptionId(item.getProductOptionId())
                    .price(item.getPrice())
                    .qty(item.getQuantity())
                    .createdDate(new Date())
                    .createdBy(currentUser.getId())
                    .build();

            return repository.orderDetailRepository.save(orderDetail);

        }).collect(Collectors.toList());

        repository.cartRepository.clearCart(currentUser.getId());


        return new ResData<>(order.getId(), PuddyCode.CREATED);
    }

    private Long getTotal(List<CartResponse> cart, OrderRequest req) {
        Long discountPrice = 0L;
        Long shopPrice = req.getTotal();
//        if(StringUtils.isNullOrEmpty(req.getDiscountCode())) {
//            DiscountEntity discountEntity = repository.discountRepository.findByCode(req.getDiscountCode());
//            discountPrice = (discountEntity.getPercentDiscount()*shopPrice)/100;
//        }
        Long shipPrice = StringUtils.isNullOrEmpty(req.getShipPrice()) ? 0L : Long.valueOf(req.getShipPrice());
        return Math.max(shopPrice, 0L) ;
//        + Math.max(shipPrice, 0L)
    }

    @Override
    public Object getMyOrders(CurrentUser currentUser) {
        List<OrderEntity> orders = repository.orderRepository.getMyOrder(currentUser.getId());

        ListOrderRes res = new ListOrderRes();

        res.setOrderRes(
                orders.stream().map(item -> {
                    AddressEntity address = repository.addressRepository.findById(item.getAddressId())
                            .orElseThrow(() -> new PuddyException(PuddyCode.ADDRESS_NOT_FOUND));
                    String s5 = ", ";
                    String addressOrder = address.getAddressDetail().concat(s5).concat(address.getWardName()).concat(s5).concat(address.getDistrictName()).concat(s5).concat(address.getProvinceName());

                    OrderResponse orderRes = OrderResponse.builder()
                            .orderCode(item.getCode())
                            .orderId(item.getId())
                            .status(StatusEnum.from(item.getStatus()))
                            .createDate(DateUtils.parseDateToStr(DateUtils.F_DDMMYYYYHHMMSS, item.getCreatedDate()))
                            .totalPrice(MoneyUtils.format(item.getTotal()))
                            .payed(item.getPayed())
                            .address(addressOrder)
                            .statusValue(StatusEnum.from(item.getStatus()).getName())
                            .build();

                    return orderRes;

                }).collect(Collectors.toList())
        );

        return new ResData<>(res, PuddyCode.OK);
    }

    @Override
    public Object search(CurrentUser currentUser, OrderSearch req) {
        log.info("----- My order search ----- ");
        AuthValidator.checkCustomer(currentUser);
        Pageable pageable = PageableUtils.getPageable(req.getPageReq());
        if (StringUtils.isNullOrEmpty(req.getTextSearch())) {
            req.setTextSearch("");
        }

        req.setTextSearch("%" + req.getTextSearch()
                .toUpperCase(Locale.ROOT)
                .trim() + "%");
        StatusEnum status = StatusEnum.from(req.getStatus());
        String statusStr = status == null ? null : status.name();
        Page<OrderEntity> orderPage = repository.orderRepository.search(req.getTextSearch(), statusStr, pageable);

        if (orderPage.isEmpty()) {
            return PageData.setEmpty(req.getPageReq());
        }

        return PageData.setResult(
                orderPage.getContent()
                        .stream()
                        .map(o -> {
                            AddressEntity address = repository.addressRepository.findById(o.getAddressId())
                                    .orElseThrow(() -> new PuddyException(PuddyCode.ADDRESS_NOT_FOUND));
                            String s5 = ", ";
                            String addressOrder = address.getAddressDetail().concat(s5).concat(address.getWardName()).concat(s5).concat(address.getDistrictName()).concat(s5).concat(address.getProvinceName());

                            OrderResponse response = OrderResponse.builder()
                                    .orderId(o.getId())
                                    .orderCode(o.getCode())
                                    .status(StatusEnum.from(o.getStatus()))
                                    .statusValue(StatusEnum.from(o.getStatus()).getName())
                                    .address(addressOrder)
                                    .createDate(DateUtils.toStr(o.getCreatedDate(), DateUtils.F_DDMMYYYYHHMM))
                                    .totalPrice(MoneyUtils.format(o.getTotal()))
                                    .payed(o.getPayed())
                                    .build();

                            return response;
                        }).collect(Collectors.toList()),
                orderPage.getNumber(),
                orderPage.getSize(),
                orderPage.getTotalElements()
        );
    }

    @Override
    public ResData<String> cancelOrder(CurrentUser currentUser, CancelOrder dto) {
        log.info("----- OrderService cancel order start -----");
        if (currentUser.getRole().equals(RoleEnum.ROLE_CUSTOMER)) {
            CancelOrderValidator.validCancelOrder(dto);
            OrderEntity order = repository.orderRepository.findById(dto.getOrderId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy hóa đơn! "));

            List<ProductOrderDetail> orderDetail = repository.orderDetailRepository.getProductOrder(order.getId());

            if (order.getStatus().equals(StatusEnum.PENDING.name())) {

                Set<String> productOptionIds = orderDetail
                        .stream()
                        .map(ProductOrderDetail::getProductOptionId)
                        .collect(Collectors.toSet());

                Map<String, ProductOptionEntity> productOptionEntityMap = repository.productOptionRepository.findAllById(productOptionIds)
                        .stream()
                        .collect(Collectors.toMap(ProductOptionEntity::getId, Function.identity(), (a, b) -> a));

                List<ProductOptionEntity> productOption = new ArrayList<>();

                orderDetail.stream().map(item -> {
                    ProductOptionEntity productOptionEntity = Optional.ofNullable(productOptionEntityMap.get(item.getProductOptionId()))
                            .orElseThrow(() -> new PuddyException(PuddyCode.PRODUCT_OPTION_NOT_FOUND));

                    productOptionEntity.setQty(productOptionEntity.getQty() + item.getQuantity());
                    return productOption.add(productOptionEntity);
                }).collect(Collectors.toList());

                repository.productOptionRepository.saveAll(productOption);

                OrderStatusEntity orderStatus = OrderStatusEntity.builder()
                        .id(UidUtils.generateUid())
                        .status(StatusEnum.CANCEL)
                        .note(dto.getNote())
                        .createdDate(new Date())
                        .orderId(order.getId())
                        .createdBy(order.getUserId())
                        .build();
                repository.orderStatusRepository.save(orderStatus);

                order.setStatus(StatusEnum.CANCEL.name());
                order.setUpdatedBy(currentUser.getId());
                order.setUpdatedDate(new Date());
                repository.orderRepository.save(order);

                return ResData.ok(order.getId(), "Hủy đơn hàng thành công !");
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, PuddyConst.Messages.FORBIDDEN);
    }

    @Override
    public Object getMyOrder4Admin(CurrentUser currentUser, ListOrderRequest request) {
        AuthValidator.checkAdmin(currentUser);
        List<OrderEntity> orders = repository.orderRepository.findAll();

        ListOrderRes res = new ListOrderRes();

        Set<String> addressIds = orders.stream().map(OrderEntity::getAddressId).collect(Collectors.toSet());

        Map<String, AddressEntity> addressEntityMap = repository.addressRepository.findAllById(addressIds)
                .stream()
                .collect(Collectors.toMap(AddressEntity::getId, Function.identity(), (a, b) -> a));

        res.setOrderRes(
                orders.stream().map(item -> {
                    AddressEntity address = Optional.ofNullable(addressEntityMap.get(item.getAddressId()))
                            .orElseThrow(() -> new PuddyException(PuddyCode.ADDRESS_NOT_FOUND));

                    String addressOrder = address.getAddressDetail().concat(COMMA).concat(address.getWardName()).concat(COMMA).concat(address.getDistrictName()).concat(COMMA).concat(address.getProvinceName());

                    OrderResponse orderRes = OrderResponse.builder()
                            .orderCode(item.getCode())
                            .orderId(item.getId())
                            .status(StatusEnum.from(item.getStatus()))
                            .createDate(DateUtils.parseDateToStr(DateUtils.F_DDMMYYYYHHMMSS, item.getCreatedDate()))
                            .totalPrice(MoneyUtils.format(item.getTotal()))
                            .payed(item.getPayed())
                            .address(addressOrder)
                            .statusValue(StatusEnum.from(item.getStatus()).getName())
                            .build();

                    return orderRes;

                }).collect(Collectors.toList())
        );

        List<OrderResponse> finalList = new ArrayList<>(res.getOrderRes());

        if (!StringUtils.isNullOrEmpty(request.getTextSearch())) {
            finalList = res.getOrderRes().stream()
                    .filter(orderResponse -> orderResponse.getAddress().matches(request.getTextSearch())
                            || orderResponse.getOrderCode().matches(request.getTextSearch()))
                    .collect(Collectors.toList());

        }

        if (!StringUtils.isNullOrEmpty(request.getStartDate()) && StringUtils.isNullOrEmpty(request.getEndDate())) {
            Date startDate = DateUtils.toDate(request.getStartDate(), DateUtils.F_DDMMYYYYHHMMSS);
            Date endDate = DateUtils.toDate(request.getEndDate(), DateUtils.F_DDMMYYYYHHMMSS);
            finalList = res.getOrderRes().stream()
                    .filter(orderResponse -> DateUtils.toDate(request.getStartDate(), DateUtils.F_DDMMYYYYHHMMSS).after(startDate)
                            && DateUtils.toDate(request.getEndDate(), DateUtils.F_DDMMYYYYHHMMSS).before(endDate))
                    .collect(Collectors.toList());
        }

//        if (ObjectUtils.allNotNull(request.getPayed())) {
//            finalList = res.getOrderRes().stream()
//                    .filter(orderResponse -> Optional.ofNullable(orderResponse.getPayed())
//                            .filter(i -> i.equals(request.getPayed()))
//                            .orElse(false))
//                    .collect(Collectors.toList());
//        }

        if(!StringUtils.isNullOrEmpty(request.getStatusValue())) {
            finalList = res.getOrderRes().stream()
                    .filter(orderResponse -> orderResponse.getStatusValue().equals(request.getStatusValue()))
                    .collect(Collectors.toList());
        }

        res.getOrderRes().clear();
        res.setOrderRes(finalList);

        return new ResData<>(res, PuddyCode.OK);
    }

    @Override
    public Object detail(CurrentUser currentUser, String id) {
        if (!repository.orderRepository.existsById(id)) {
            return new ResData<>(true);
        }

        AuthValidator.checkRole(currentUser, RoleEnum.ROLE_ADMIN, RoleEnum.ROLE_ADMIN);
        return repository.orderRepository.detail4Admin(currentUser, id);
    }

    @Override
    @Transactional
    public Object changeStatus(CurrentUser currentUser, ChangeStatusDto dto) {
        AuthValidator.checkRole(currentUser, RoleEnum.ROLE_ADMIN, RoleEnum.ROLE_CUSTOMER);
        OrderEntity order = repository.orderRepository.findByIdV1(dto.getId());
        StatusEnum status = StatusEnum.from(dto.getStatus().toUpperCase(Locale.ROOT));
        if (status == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Trạng thái không hợp lệ!");
        }

        order.setStatus(status.name());
        order.setUpdatedBy(currentUser.getId());
        order.setUpdatedDate(new Date());
        repository.orderRepository.save(order);
        OrderStatusEntity orderStatus = OrderStatusEntity.builder()
                .id(UidUtils.generateUid())
                .status(status)
                .createdBy(currentUser.getId())
                .createdDate(new Date())
                .orderId(dto.getId())
                .note(dto.getNote())
                .build();
        repository.orderStatusRepository.save(orderStatus);
        return ResData.ok(order.getId(), "Chuyển trạng thái thành công");
    }
}
