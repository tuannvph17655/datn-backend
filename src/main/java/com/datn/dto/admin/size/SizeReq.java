package com.datn.dto.admin.size;

import com.datn.utils.base.rest.PageReq;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SizeReq {
    private String id;
    private String name;
    private String code;
    private PageReq pageReq;
}
