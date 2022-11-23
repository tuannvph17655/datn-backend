package com.datn.service;


import com.datn.dto.admin.color.ColorReq;
import com.datn.dto.admin.color.ColorRes;
import com.datn.dto.customer.product.ColorResponse;
import com.datn.utils.base.enum_dto.ColorDto;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;

import java.util.ArrayList;
import java.util.List;

public interface ColorService {
//    ResData<List<ColorResponse>> getListColor();
    ResData<String> create(CurrentUser currentUser, ColorDto dto);

    ResData<String> delete(CurrentUser currentUser, ColorDto dto);

    ResData<String> update(CurrentUser currentUser, ColorDto dto);

	ResData<List<ColorResponse>> getListColor();

	PageData<ColorRes> getListColor4admin(CurrentUser currentUser, ColorReq colorReq) ;
}
