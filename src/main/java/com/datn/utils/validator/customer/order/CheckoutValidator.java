package com.datn.utils.validator.customer.order;

import com.datn.dto.customer.order.OrderRequest;
import com.datn.utils.common.StringUtils;
import com.datn.utils.common.ValidatorUtils;

public class CheckoutValidator {
    private static final String ADDRESS_ID = "Mã địa chỉ";
    private static final String NOTE = "Ghi chú";
    private static final String SHIPPING_METHOD = "Phương thức vận chuyển";
    private static final String SHIP_PRICE = "Phí ship";

    public CheckoutValidator() {
    }

    public static void validCheckout(OrderRequest req){
        ValidatorUtils.validNullOrEmpty(ADDRESS_ID,req.getAddressId());
//        ValidatorUtils.validNullOrEmpty(NOTE,req.getNote());
        ValidatorUtils.validNullOrEmpty(SHIPPING_METHOD,req.getShipMethod());
        if (!StringUtils.isNullOrEmpty(req.getNote())) {
            ValidatorUtils.validLength(NOTE, req.getNote(), 255, true);
        }
        ValidatorUtils.validNullOrEmpty(SHIP_PRICE, req.getShipPrice());
        ValidatorUtils.validOnlyNumber(SHIP_PRICE, req.getShipPrice());
        ValidatorUtils.validLongValueMustBeMore(SHIP_PRICE, req.getShipPrice(), 0L);
    }
}
