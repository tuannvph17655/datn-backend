package com.datn.utils.constants.enums;

import com.datn.utils.common.StringUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyException;

public enum StatusEnum {
    /**
     * Trạng thái đơn hàng
     */
    PROCESSING("Khách hàng đang mua - chưa checkin"),
    PENDING("Chờ xác nhận"),
    PAYED("Đã thanh toán"),
    CANCEL("Đã hủy"),
    REJECT("Bị từ chối"),
    ACCEPT("Được chấp nhận"),
    SHIPPING("Đang giao hàng"),
    RECEIVED("Đã nhận được hàng"),
    UNRECEIVED("Giao hàng không thành công ");

    private String name;

    StatusEnum(String name) {
        this.name = name;
    }

    public static StatusEnum from(String text) {
        if (StringUtils.isNullOrEmpty(text)) {
            return null;
        }
        for (StatusEnum item : StatusEnum.values()) {
            if (item.name().equals(text)) {
                return item;
            }
        }
        throw new PuddyException(PuddyCode.STATUS_INVALID);
    }

    public String getName() {
        return name;
    }
}
