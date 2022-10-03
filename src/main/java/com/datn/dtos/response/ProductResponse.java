package com.datn.dtos.response;

import com.datn.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private Long categoryId;
    private String description;
    private boolean active;
    private int quantity;
    private double price;
    private double discoundPrice;
    private double weight;
    private double height;

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .active(product.isActive())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .discoundPrice(product.getDiscoundPrice())
                .weight(product.getWeight())
                .height(product.getWeight())
                .build();
    }
}
