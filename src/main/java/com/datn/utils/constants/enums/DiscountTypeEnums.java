package com.datn.utils.constants.enums;

import com.datn.utils.common.StringUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyException;

public enum DiscountTypeEnums {
    PERCENT("Theo phần trăm"),
    PRICE("Theo số tiền"),
    SHIP("Miễn phí vận chuyển");

    private String name;

    DiscountTypeEnums(String name) {
        this.name = name;
    }

    public static DiscountTypeEnums from(String text) {
        if (StringUtils.isNullOrEmpty(text)) {
            throw new PuddyException(PuddyCode.DISCOUNT_TYPE_INVALID);
        }
        for (DiscountTypeEnums item : DiscountTypeEnums.values()) {
            if (item.name().equals(text)) {
                return item;
            }
        }
        return null;
    }
}
