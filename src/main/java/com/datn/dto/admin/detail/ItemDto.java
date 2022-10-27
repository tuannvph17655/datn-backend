package com.datn.dto.admin.detail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    //productId
    private String id;

    private String name;

    private String color;

    private String size;

    private String image;

    private String material;

    private Long price;

    private Long qty;

    private String category;

    private Long total;

    private String priceFmt;

    private String totalFmt;

    public ItemDto(String id, String name, String color, String size, String image, String material, Long price, Long qty, String category, Long total) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.size = size;
        this.image = image;
        this.material = material;
        this.price = price;
        this.qty = qty;
        this.category = category;
        this.total = total;
    }
}
