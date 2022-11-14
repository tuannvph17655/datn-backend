package com.datn.dto.admin.product.create_update;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String id;
    private String name;
    private List<String> des;
    private String categoryId;
    private String materialId;
//    private List<OptionDto> options;
}
