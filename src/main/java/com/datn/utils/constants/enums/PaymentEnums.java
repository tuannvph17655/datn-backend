package com.datn.utils.constants.enums;

public enum PaymentEnums {
    COD("COD", "Thanh toán khi giao hàng"),
    VNPAY("VNPAY", "Thanh toán qua VN-Pay"),
    ZALOPAY("ZALOPAY", "Thanh toán qua Zalo-Pay"),
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
}
