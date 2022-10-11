package com.datn.dto.customer.product;

import com.datn.utils.base.rest.PageReq;
import lombok.Data;

@Data
public class ProductReq {
    private String id;
    private Boolean active;
    private String textSearch;
    private String minPrice;
    private String maxPrice;
    private PageReq pageReq;
}
