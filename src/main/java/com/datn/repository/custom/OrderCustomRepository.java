package com.datn.repository.custom;


import com.datn.dto.admin.detail.DetailRes;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;

public interface OrderCustomRepository {
    ResData<DetailRes> detail4Admin(CurrentUser currentUser, String id);
}
