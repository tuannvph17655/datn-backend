package com.datn.dto.customer.cart.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private String cartId;
    private String productId;
    private String productOptionId;
    private String productName;
    private String sizeName;
    private String colorName;
    private Integer quantity;
    private Long quantityAvailable;
    private Long price;
    private String image;
    private Long subtotal;
    private String categoryId;
}
