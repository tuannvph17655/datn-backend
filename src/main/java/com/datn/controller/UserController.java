package com.datn.controller;

import com.datn.dto.customer.user.register.RegisterDto;
import com.datn.utils.base.PuddyController;
import com.datn.utils.common.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Slf4j
public class UserController extends PuddyController {

    @Operation(summary = "API thông tin cá nhân")
    @GetMapping("/personal")
    public ResponseEntity<Object> personal() {
        log.info("start api /api/v1/user/personal");
        return ResponseEntity.ok(service.userInfoService.personal(getCurrentUser()));
    }
    @PostMapping("/customer/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto payload) {
        log.info("start api /api/v1/user/customer/register with payload: {}", JsonUtils.toJson(payload));
        return ResponseEntity.ok(service.customerDetailService.register(payload));
    }



    @GetMapping("/my-profile")
    public ResponseEntity<?> getMyProfile() {
        return ResponseEntity.ok(service.userService.getCurrentUserProfile());
    }
}
