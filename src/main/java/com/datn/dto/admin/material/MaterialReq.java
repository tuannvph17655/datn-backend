package com.datn.dto.admin.material;

import com.datn.utils.base.rest.PageReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MaterialReq {
    private  String name;
    private PageReq pageReq;
}
