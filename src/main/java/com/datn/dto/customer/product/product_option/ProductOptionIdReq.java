package com.datn.dto.customer.product.product_option;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductOptionIdReq {
    @NotNull
    private String sizeId;
    @NotNull
    private String colorId;
    @NotNull
    private String productId;
}
