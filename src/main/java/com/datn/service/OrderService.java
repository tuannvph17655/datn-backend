package com.datn.service;

import com.datn.dto.admin.order.change_status.ChangeStatusDto;
import com.datn.dto.admin.order.search.ListOrderRequest;
import com.datn.dto.customer.order.CancelOrder;
import com.datn.dto.customer.order.OrderRequest;
import com.datn.dto.customer.order.OrderSearch;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;

public interface OrderService {
    Object checkout(CurrentUser currentUser, OrderRequest req);
    ResData<String> cancelOrder(CurrentUser currentUser, CancelOrder dto);
    Object getMyOrders(CurrentUser currentUser);
    Object getMyOrder4Admin(CurrentUser currentUser, ListOrderRequest request);
    Object search(CurrentUser currentUser, OrderSearch req);

    ResData<String> rejectOrder(CurrentUser currentUser, CancelOrder dto);
    Object detail(CurrentUser currentUser, String id);
    Object changeStatus(CurrentUser currentUser, ChangeStatusDto dto);

    Object getListOrderStatus(CurrentUser currentUser);
}
