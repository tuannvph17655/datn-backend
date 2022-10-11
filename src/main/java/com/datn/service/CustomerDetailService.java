package com.datn.service;


import com.datn.dto.customer.user.register.RegisterDto;

public interface CustomerDetailService {
    Object register(RegisterDto payload);

    Object sendMail4ForgotPass(String email);
}
