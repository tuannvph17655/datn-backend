package com.datn.repository.custom;

import com.datn.dto.customer.product.ProductReq;
import com.datn.dto.customer.product.ProductResponse;
import com.datn.utils.base.rest.PageData;

public interface ProductCustomRepository {
    //    PageData<ProductResponse> search(CurrentUser currentUser, ProductReq req);
    PageData<ProductResponse> search(ProductReq req);

    Object searchV2(com.datn.dto.customer.product.search.ProductReq req);
}
