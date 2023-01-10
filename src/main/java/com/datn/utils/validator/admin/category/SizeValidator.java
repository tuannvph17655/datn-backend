package com.datn.utils.validator.admin.category;

import com.datn.utils.base.enum_dto.SizeDto;
import com.datn.utils.constants.PuddyConst;
import com.datn.utils.validator.ValidateUtils;

public class SizeValidator {
    public SizeValidator() {
    }

    public static void validateCreate(SizeDto dto) {
        ValidateUtils.validBlank(PuddyConst.SizeFields.NAME,dto.getName());
        ValidateUtils.validBlank(PuddyConst.SizeFields.CODE,dto.getCode());
        ValidateUtils.isValidMaxLength(PuddyConst.SizeFields.NAME,dto.getName(),250);
        ValidateUtils.isValidRangeLength(PuddyConst.SizeFields.NAME,dto.getCode(),5,15);
    }
}
