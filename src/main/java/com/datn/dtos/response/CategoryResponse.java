package com.datn.dtos.response;

import com.datn.entity.Category;
import com.datn.utils.common.CopyUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private boolean active;

    public static CategoryResponse from(Category category) {
        CategoryResponse categoryResponse = CategoryResponse
                .builder()
                .id(category.getId())
                .name(category.getName())
                .active(category.isActive())
                .build();
        return categoryResponse;
    }
}
