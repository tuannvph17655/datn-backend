package com.datn.dto.admin.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRes {
    private String id;
    private String name;
    private String des;
    private String image;
    private Boolean active;
    private Date createdDate;
    private String createdDateValue;
    /**
     * Số lượng sản phẩm
     */
    private Long productNumber;
}
