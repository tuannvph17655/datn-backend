package com.datn.dto.customer.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    @NotNull
    private String productOptionId;
    @NotNull
    private Integer quantity;
}
