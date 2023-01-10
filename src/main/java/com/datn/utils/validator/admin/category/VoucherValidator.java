package com.datn.utils.validator.admin.category;

import com.datn.dto.admin.voucher.VoucherDto;
import com.datn.service.VoucherService;
import com.datn.utils.constants.PuddyConst;
import com.datn.utils.validator.ValidateUtils;

public class VoucherValidator {

    public VoucherValidator() {
    }

    public static void validCreate(VoucherDto dto) {
        ValidateUtils.validBlank(PuddyConst.VoucherFields.CODE,dto.getCode());
        ValidateUtils.validBlank(PuddyConst.VoucherFields.DES, dto.getDes());
        ValidateUtils.validBlank(PuddyConst.VoucherFields.START_DATE,dto.getStartDate().toString());
        ValidateUtils.validBlank(PuddyConst.VoucherFields.END_DATE,dto.getEndDate().toString());
    }
}
