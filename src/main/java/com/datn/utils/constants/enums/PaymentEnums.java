package com.datn.utils.constants.enums;

import com.datn.utils.common.StringUtils;

public enum PaymentEnums {
    COD("COD", "Thanh toán khi giao hàng"),
    VNPAY("VNPAY", "Thanh toán qua VN-Pay"),
    ZALOPAY("ZALOPAY", "Thanh toán qua Zalo-Pay"),
    ATM("ATM","Chuyển khoản qua ngân hàng")
    ;
    private final String code;
    private final String name;

    PaymentEnums(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static PaymentEnums from(String text) {
        if (StringUtils.isNullOrEmpty(text)) {
            return null;
        }
        for (PaymentEnums item : PaymentEnums.values()) {
            if (item.name().equals(text)) {
                return item;
            }
        }
        return null;
    }
}
