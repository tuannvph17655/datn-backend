package com.datn.service.impl;

import com.datn.dto.admin.category.CategoryDto;
import com.datn.dto.admin.category.CategoryReq;
import com.datn.dto.admin.category.CategoryRes;
import com.datn.service.CategoryService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public PageData<CategoryRes> search(CurrentUser currentUser, CategoryReq req) throws PuddyException {
        return null;
    }

    @Override
    public ResData<String> create(CurrentUser currentUser, CategoryDto dto) {
        return null;
    }

    @Override
    public ResData<String> delete(CurrentUser currentUser, CategoryDto dto) {
        return null;
    }

    @Override
    public ResData<String> update(CurrentUser currentUser, CategoryDto dto) {
        return null;
    }

    @Override
    public ResData<CategoryRes> detail(CurrentUser currentUser, CategoryDto dto) {
        return null;
    }
}
