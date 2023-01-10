package com.datn.controller.admin;

import com.datn.service.VoucherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/voucher")
@AllArgsConstructor
public class AdminVoucherController {
    private final VoucherService voucherService;
}
