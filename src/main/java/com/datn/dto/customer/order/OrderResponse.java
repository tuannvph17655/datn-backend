package com.datn.dto.customer.order;

import com.datn.utils.constants.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String orderId;
    private String orderCode;
    private String createDate;
    private String address;
    private String totalPrice;
    private Boolean payed;
    private OrderStatus status;//trang thai van chuyen
    private String statusValue;
}
