package com.datn.dto.customer.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String id;
    private String addressId;
    private String note;
    private String paymentMethod;
    private String shipPrice;
    private String shipMethod;
    private Long total;//cart + ship
    private String discountCode;
}
