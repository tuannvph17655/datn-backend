package com.datn.service;

import com.datn.dto.customer.address.AddressReq;
import com.datn.utils.base.rest.CurrentUser;

public interface AddressService {
    Object getListAddress(CurrentUser currentUser);
    Object createAddress(CurrentUser currentUser, AddressReq req);
    Object updateAddress(CurrentUser currentUser, AddressReq req);
    void deleteAddress(CurrentUser currentUser,String id);
    void setAddressDefault(CurrentUser currentUser,String id);
    Object getAddressById(CurrentUser currentUser,String id);
}
