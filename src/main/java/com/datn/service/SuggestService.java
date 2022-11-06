package com.datn.service;

import com.datn.dto.customer.product.ColorResponse;
import com.datn.dto.customer.suggest.SuggestDto;
import com.datn.dto.customer.suggest.SuggestResponse;
import com.datn.utils.base.enum_dto.ColorDto;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;

import java.util.List;

public interface SuggestService {
    ResData<List<SuggestResponse>> getListSuggest();

    ResData<String> create(CurrentUser currentUser, SuggestDto dto);

    ResData<String> delete(CurrentUser currentUser, SuggestDto dto);

    ResData<String> update(CurrentUser currentUser, SuggestDto dto);

}
