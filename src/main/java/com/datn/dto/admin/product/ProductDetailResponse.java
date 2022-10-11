package com.datn.dto.admin.product;

import com.datn.dto.admin.product_option.ProductOptionResponse;
import com.datn.dto.customer.review.response.ReviewResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse {

    private String productId;
    private String productName;
    private String categoryName;
    private String material;
    private Integer countRating;//số lượt đánh giá
    private Float avgRating;
    private String description;
    private List<ProductOptionResponse> productOptions;
    private List<ReviewResponse> review;

}
