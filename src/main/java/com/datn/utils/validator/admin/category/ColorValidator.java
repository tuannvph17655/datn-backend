package com.datn.utils.validator.admin.category;

import com.datn.utils.base.enum_dto.ColorDto;
import com.datn.utils.constants.PuddyConst;
import com.datn.utils.validator.ValidateUtils;

public class ColorValidator {
    private ColorValidator() {
    }
    public static void validateColor(ColorDto colorDto) {
        ValidateUtils.validBlank(PuddyConst.ColorFields.NAME, colorDto.getName());
        ValidateUtils.validBlank(PuddyConst.ColorFields.HEX, colorDto.getHex());
        ValidateUtils.isValidMaxLength(PuddyConst.ColorFields.NAME,colorDto.getName(),250);
    }
}
