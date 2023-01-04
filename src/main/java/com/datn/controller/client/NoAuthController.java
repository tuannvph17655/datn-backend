package com.datn.controller.client;

import com.datn.dto.customer.order.OrderRequest;
import com.datn.dto.customer.user.register.RegisterDto;
import com.datn.utils.base.PuddyController;
import com.datn.utils.common.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/no-auth")
@RequiredArgsConstructor
@Slf4j
public class NoAuthController extends PuddyController {
    @PostMapping("/customer/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto payload) {
        log.info("start api /api/v1/no-auth/customer/register with payload: {}", JsonUtils.toJson(payload));
        return ResponseEntity.ok(service.customerDetailService.register(payload));
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest req) {
        log.info("----- START API /api/v1/order/checkin ------");
        return ResponseEntity.ok(service.orderService.checkout(getCurrentUser(), req));
    }
}
