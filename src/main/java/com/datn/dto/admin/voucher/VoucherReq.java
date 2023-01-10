package com.datn.dto.admin.voucher;

import com.datn.utils.base.rest.PageReq;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VoucherReq {
    private String textSearch;
    private Boolean active;
    private String status;
    private String startDate;
    private String endDate;

    private PageReq pageReq;

}
