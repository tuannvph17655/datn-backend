package com.datn.utils.constants.enums;

import com.datn.utils.common.StringUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyException;
import lombok.Getter;
@Getter
public enum OrderStatus {
    /**
     * Trạng thái đơn hàng
     */
    PENDING("Chờ xác nhận"),
    PAYED("Đã thanh toán"),
    CANCEL("Đã hủy"),
    REJECT("Bị từ chối"),
    ACCEPT("Được chấp nhận"),
    SHIPPING("Đang giao hàng"),
    RECEIVED("Đã nhận được hàng"),
    UNRECEIVED("Giao hàng không thành công ");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public static OrderStatus from(String text) {
        if (StringUtils.isNullOrEmpty(text)) {
            return null;
        }
        for (OrderStatus item : OrderStatus.values()) {
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
