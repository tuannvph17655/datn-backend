package com.datn.service;

import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.utils.base.enum_dto.SizeDto;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;

import java.util.List;

public interface SizeService {
    ResData<List<SizeResponse>> getAllSize();
    ResData<String> create(CurrentUser currentUser, SizeDto dto);

    ResData<String> delete(CurrentUser currentUser, SizeDto dto);

    ResData<String> update(CurrentUser currentUser, SizeDto dto);

}
