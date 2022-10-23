package com.datn.service;

import com.datn.dto.customer.order.CancelOrder;
import com.datn.dto.customer.order.OrderRequest;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;

public interface OrderService {
    Object checkout(CurrentUser currentUser, OrderRequest req);
//    Object getMyOrders(CurrentUser currentUser);
    ResData<String> cancelOrder(CurrentUser currentUser, CancelOrder dto);
    Object getMyOrders(CurrentUser currentUser);
//    Object search(CurrentUser currentUser, OrderSearch req);
}
