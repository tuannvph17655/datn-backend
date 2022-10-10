package com.datn.dto.admin.category;

import com.datn.utils.base.rest.PageReq;
import lombok.Data;

@Data
public class CategoryReq {
    private String id;
    private Boolean active;
    private String textSearch;
    private PageReq pageReq;
}
