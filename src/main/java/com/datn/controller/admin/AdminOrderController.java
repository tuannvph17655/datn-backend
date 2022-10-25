package com.datn.controller.admin;

import com.datn.utils.base.PuddyController;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/order")
@RequiredArgsConstructor
@Slf4j
public class AdminOrderController extends PuddyController {

    @Operation(summary = "My Orders")
    @GetMapping("/listOrder")
    public ResponseEntity<?> getMyOrder() {
        log.info("START API /api/v1/admin/order/listOrder");
        return ResponseEntity.ok(service.orderService.getMyOrder4Admin(getCurrentUser()));
    }

}
