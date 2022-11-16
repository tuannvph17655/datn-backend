package com.datn.dto.customer.order.order_detail;

import com.datn.dto.admin.order.CustomerInfoRes;
import com.datn.utils.constants.enums.StatusEnum;
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
    private StatusEnum status;//trang thai van chuyen
    private String statusValue;

    private CustomerInfoRes customerInfoRes;
}
