package com.datn.dto.customer.product.productOption;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductOptionIdRes {
    private String productOptionId;
    private Long quantity;
    private Long price;
}
