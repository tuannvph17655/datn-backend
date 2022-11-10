package com.datn.service;


import com.datn.dto.admin.product.create_update.ProductDto;
import com.datn.dto.customer.product.ProductReq;
import com.datn.utils.base.rest.CurrentUser;

/**
 * @author myname
 */
public interface AdminProductService {
    Object search(CurrentUser currentUser, ProductReq req);

    Object detail(CurrentUser currentUser, String id);

    Object create(CurrentUser currentUser, ProductDto dto);
}
