package com.datn.service;


import com.datn.dto.customer.cart.request.CartRequest;
import com.datn.dto.customer.cart.response.CountCartItem;
import com.datn.dto.customer.cart.response.ListCartResponse;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;

public interface CartService {
    ResData<String> addToCart(CurrentUser currentUser, CartRequest cart);

    ResData<ListCartResponse> getListCart(CurrentUser currentUser);

    ResData<String> updateCart(CurrentUser currentUser, CartRequest cart);

    ResData<String> deleteItemInCart(CurrentUser currentUser,String productOptionId);

    ResData<CountCartItem> countCartItem(CurrentUser currentUser);

    ResData<String> deleteAllCart(CurrentUser currentUser);
}
