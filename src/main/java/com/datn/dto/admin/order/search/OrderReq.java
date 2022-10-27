package com.datn.dto.admin.order.search;

import com.datn.utils.base.rest.PageReq;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReq {
    private String id;
    private String status;
    private String customerId;
    private String provinceCode;
    private String districtCode;
    private String wardCode;
    private String time;
    private String textSearch;
    private PageReq pageReq;
}
