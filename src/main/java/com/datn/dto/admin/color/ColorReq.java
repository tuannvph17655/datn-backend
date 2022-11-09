package com.datn.dto.admin.color;

import com.datn.utils.base.rest.PageReq;
import lombok.Data;

@Data
public class ColorReq {
    private String textSearch;
    private PageReq pageReq;
}
