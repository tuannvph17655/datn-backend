package com.datn.dto.admin.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomerInfoRes {
    private String phone;
    private String name;
    private String address;
}

