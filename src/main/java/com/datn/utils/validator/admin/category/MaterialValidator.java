package com.datn.utils.validator.admin.category;

import com.datn.utils.base.enum_dto.MaterialDto;
import com.datn.utils.constants.PuddyConst;
import com.datn.utils.validator.ValidateUtils;

public class MaterialValidator {
    private MaterialValidator() {

    }
    public static void validCreate(MaterialDto dto) {
        ValidateUtils.validBlank(PuddyConst.MaterialFields.NAME,dto.getName());
        ValidateUtils.validBlank(PuddyConst.MaterialFields.CODE,dto.getCode());
        ValidateUtils.isValidMaxLength(PuddyConst.MaterialFields.NAME,dto.getName(),250);
        ValidateUtils.isValidRangeLength(PuddyConst.MaterialFields.CODE,dto.getCode(),5,10);
    }
}
