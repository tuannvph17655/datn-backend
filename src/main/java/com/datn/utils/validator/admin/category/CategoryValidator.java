package com.datn.utils.validator.admin.category;

import com.datn.dto.admin.category.CategoryDto;
import com.datn.utils.constants.PuddyConst;
import com.datn.utils.validator.ValidateUtils;

public class CategoryValidator {
    private CategoryValidator() {
    }

    public static void validCreate(CategoryDto dto) {
        ValidateUtils.validBlank(PuddyConst.CategoryFields.NAME_VAL, dto.getName());
        ValidateUtils.validBlank(PuddyConst.CategoryFields.DES_VAL, dto.getDes());
        ValidateUtils.isValidRangeLength(PuddyConst.CategoryFields.NAME_VAL, dto.getName(), 4, 250);
        ValidateUtils.isValidRangeLength(PuddyConst.CategoryFields.DES_VAL, dto.getDes(), 10, 250);
    }
}
