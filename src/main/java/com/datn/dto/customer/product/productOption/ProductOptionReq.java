package com.datn.dto.customer.product.productOption;

import com.datn.utils.base.rest.PageReq;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductOptionReq {
    private String sizeId;
    private String colorId;
    private String productId;
    private PageReq pageReq;

}
