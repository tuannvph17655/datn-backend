package com.datn.service;


import com.datn.dto.admin.product.ProductDetailResponse;
import com.datn.dto.customer.product.ProductRelatedRes;
import com.datn.dto.customer.product.ProductReq;
import com.datn.dto.customer.product.ProductResponse;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;

import java.util.List;

public interface ProductService {
    ResData<ProductDetailResponse> getProductDetail(String id);

    //    PageData<ProductResponse> search(CurrentUser currentUser, ProductReq productReq);
    PageData<ProductResponse> search(ProductReq productReq);

    Object searchV2(com.datn.dto.customer.product.search.ProductReq req);

    ResData<List<ProductRelatedRes>> getRelatedProduct(String productId);
}
