package com.datn.utils.constants.enums;

public enum ApplyTypeEnums {
    ALL_PRODUCT("Tất cả sản phẩm"),
    CATEGORY("Loại sản phẩm"),
    PRODUCT("Sản phẩm");

    private String name;

    ApplyTypeEnums(String name) {
        this.name = name;
    }

    public static ApplyTypeEnums from(String text) {
        for (var item : ApplyTypeEnums.values()) {
            if (item.name().equals(text)) {
                return item;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
