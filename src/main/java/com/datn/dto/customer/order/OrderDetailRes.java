package com.datn.dto.customer.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailRes {
    private String nameOfRecipient;
    private String phoneNumber;
    private String note;
    private String orderCode;
    private Date createDate;
    private Long totalPrice;
    private Long shipPrice;
    private String shipAddress;
    private String paymentMethod;
    private boolean payed;
    private String statusOrder;

}
