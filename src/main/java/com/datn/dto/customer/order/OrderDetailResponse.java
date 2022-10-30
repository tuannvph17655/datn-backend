package com.datn.dto.customer.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {

    private String nameOfRecipient;
    private String phoneNumber;
    private String note;
    private String orderCode;
    private String createDate;
    private String totalPrice;
    private String shopPrice;
    private String shipPrice;
    private String shipAddress;
    private String paymentMethod;
    private boolean payed;
    private String statusOrder;
    private List<ProductInOrderDetail> product;

}
