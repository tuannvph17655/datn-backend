package com.datn.service;

import com.datn.utils.base.rest.CurrentUser;

public interface OrderDetailService {
    Object getOrderDetail(CurrentUser currentUser, String id);
}
