package com.datn.dto.admin.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderStatusResponse {
    private String orderStatus;
    private String orderStatusName;
}
