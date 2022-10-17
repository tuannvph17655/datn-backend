package com.datn.service.impl;

import com.datn.dto.admin.discount.type.ShipTypeDto;
import com.datn.dto.customer.cart.response.CartResponse;
import com.datn.dto.customer.order.OrderRequest;
import com.datn.entity.AddressEntity;
import com.datn.entity.DiscountEntity;
import com.datn.entity.OrderDetailEntity;
import com.datn.entity.OrderEntity;
import com.datn.entity.OrderStatusEntity;
import com.datn.entity.ProductOptionEntity;
import com.datn.service.OrderService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.StringUtils;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyException;
import com.datn.utils.constants.enums.DiscountTypeEnums;
import com.datn.utils.constants.enums.PaymentEnums;
import com.datn.utils.constants.enums.StatusEnum;
import com.datn.utils.validator.auth.AuthValidator;
import com.datn.utils.validator.customer.order.CheckoutValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final PuddyRepository repository;

    @Override
    @Transactional
    public Object checkout(CurrentUser currentUser, OrderRequest req) {
        log.info("----- OrderService create order start with payload: {}", JsonUtils.toJson(req));
//        AuthValidator.checkCustomer(currentUser);
//        CheckoutValidator.validCheckout(req);

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
                .id(UidUtils.generateUid())
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
        Long shopPrice = req.getTotal();
        Long shipPrice = StringUtils.isNullOrEmpty(req.getShipPrice()) ? 0L : Long.valueOf(req.getShipPrice());
        return Math.max(shopPrice, 0L) + Math.max(shipPrice, 0L);
    }
}
