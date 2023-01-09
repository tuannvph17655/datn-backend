package com.datn.utils.constants.enums;

public enum VoucherStatus {
    ACTIVE("Đang áp dụng"),
    INACTIVE("Ngưng áp dụng"),
    PENDING("Chưa áp dụng"),
    EXPIRED("Hết hạn");

    private final String value;

    VoucherStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
