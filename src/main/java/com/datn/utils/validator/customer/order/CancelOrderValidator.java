package com.datn.utils.validator.customer.order;

import com.datn.dto.customer.order.CancelOrder;
import com.datn.utils.common.ValidatorUtils;

public class CancelOrderValidator {
    private static final String ORDER_ID = "Mã đơn hàng";
    private static final String NOTE = "Lý do hủy đơn hàng";

    public CancelOrderValidator() {
    }

    public static void validCancelOrder(CancelOrder req){
        ValidatorUtils.validNullOrEmpty(ORDER_ID,req.getOrderId());
        ValidatorUtils.validNullOrEmpty(NOTE,req.getNote());
    }
}
