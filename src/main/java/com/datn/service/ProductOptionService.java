package com.datn.service;

import com.datn.dto.customer.product.ColorResponse;
import com.datn.dto.customer.product.productOption.ProductOptionIdReq;
import com.datn.dto.customer.product.productOption.ProductOptionIdRes;
import com.datn.dto.customer.product.productOption.ProductOptionReq;
import com.datn.dto.customer.product.productOption.ProductOptionRes;
import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;

import java.util.List;

public interface ProductOptionService {
    ResData<ProductOptionIdRes> findProductOptionId(ProductOptionIdReq req);

    ResData<List<ColorResponse>> findColorNameBySize(String sizeId, String productId);

    ResData<List<SizeResponse>> findSizeByProductId(String productId);

    PageData<ProductOptionRes> findAllByProductId(CurrentUser currentUser, ProductOptionReq productOptionReq);
}
