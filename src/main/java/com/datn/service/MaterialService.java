package com.datn.service;

import com.datn.utils.base.enum_dto.MaterialDto;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;

public interface MaterialService {
    ResData<String> create(CurrentUser currentUser, MaterialDto dto);

    ResData<String> delete(CurrentUser currentUser, MaterialDto dto);

    ResData<String> update(CurrentUser currentUser, MaterialDto dto);
}
