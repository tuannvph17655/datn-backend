package com.datn.service;

import com.datn.dto.admin.category.CategoryDto;
import com.datn.dto.admin.category.CategoryReq;
import com.datn.dto.admin.category.CategoryRes;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;

public interface CategoryService {
    PageData<CategoryRes> search(CurrentUser currentUser, CategoryReq req) throws PuddyException;

    ResData<String> create(CurrentUser currentUser, CategoryDto dto);

    ResData<String> delete(CurrentUser currentUser, CategoryDto dto);

    ResData<String> update(CurrentUser currentUser, CategoryDto dto);

    ResData<CategoryRes> detail(CurrentUser currentUser, CategoryDto dto);
}
