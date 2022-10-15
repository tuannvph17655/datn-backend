package com.datn.service;

import com.datn.dto.customer.user.RegisterDto;

public interface UserService {

    Object registerCustomer(RegisterDto body);
    Object getCurrentUserProfile();
}
