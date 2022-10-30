package com.datn.service.impl;

import com.datn.dto.customer.order.OrderDetailRes;
import com.datn.dto.customer.order.OrderDetailResponse;
import com.datn.dto.customer.order.ProductInOrderDetail;
import com.datn.service.OrderDetailService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.DateUtils;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.MoneyUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.enums.PaymentEnums;
import com.datn.utils.constants.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {
    private final PuddyRepository repository;

    @Override
    public Object getOrderDetail(CurrentUser currentUser, String orderId) {

        if (!repository.orderRepository.existsById(orderId)) {
            return new ResData<>(true);
        }

        OrderDetailRes orderDetail = repository.orderDetailRepository.getOrderDetail(orderId);
        log.info("before ordetail {} " , JsonUtils.toJson(orderDetail));
        PaymentEnums payment = PaymentEnums.from(orderDetail.getPaymentMethod());

        OrderDetailResponse orderDetailResponse = OrderDetailResponse.builder()
                .nameOfRecipient(orderDetail.getNameOfRecipient())
                .phoneNumber(orderDetail.getPhoneNumber())
                .totalPrice(MoneyUtils.format(orderDetail.getTotalPrice()))
                .shipPrice(MoneyUtils.format(orderDetail.getShipPrice()))
                .note(orderDetail.getNote())
                .payed(orderDetail.isPayed())
                .shipAddress(orderDetail.getShipAddress())
                .shopPrice(orderDetail.getShopTotal().toString())
                .paymentMethod(payment == null ? null : payment.getName())
                .orderCode(orderDetail.getOrderCode())
                .createDate(DateUtils.parseDateToStr(DateUtils.F_DDMMYYYYHHMMSS,orderDetail.getCreateDate()))
                .statusOrder(StatusEnum.from(orderDetail.getStatusOrder()) == null ? null : StatusEnum.from(orderDetail.getStatusOrder()).getName())
                .build();

        List<ProductInOrderDetail> productOrder = repository.orderDetailRepository.getProductList(orderId);

        orderDetailResponse.setProduct(productOrder);
//        log.info("order detail : ", JsonUtils.toJson(orderDetailResponse));
        return new ResData<>(orderDetailResponse, PuddyCode.OK);
    }
}
