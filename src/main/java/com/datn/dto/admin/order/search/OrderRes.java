package com.datn.dto.admin.order.search;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
public class OrderRes {
    private String id;
    private String code;
    private String customerName;
    private String phone;
    private Date orderDate;
    private String orderDateValue;
    private String addressCombination;
    private Long total;
    private String totalValue;
    private String note;
    private String type;
    private String status;
    private String customerId;
    private List<OptionDto> options;
    private String payment;
    private String paymentDetail;
    private Boolean payed;
    private String addressExtract;
}
