package com.datn.controller;

import com.datn.dto.customer.order.CancelOrder;
import com.datn.dto.customer.order.OrderRequest;
import com.datn.dto.customer.order.OrderSearch;
import com.datn.utils.base.PuddyController;
import com.datn.utils.common.JsonUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController extends PuddyController {
    @ApiOperation("API checkout")
    @PostMapping("/checkout")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest req) {
        log.info("----- START API /api/v1/order/checkin ------");
        return ResponseEntity.ok(service.orderService.checkout(getCurrentUser(), req));
    }

    @Operation(summary = "My Orders")
    @GetMapping("/myOrders")
    public ResponseEntity<?> getMyOrder() {
        log.info("START API /api/v1/order/myOrder");
        return ResponseEntity.ok(service.orderService.getMyOrders(getCurrentUser()));
    }

    @Operation(summary = "API hủy đơn hàng")
    @PostMapping("/cancelOrder")
    public ResponseEntity<?> cancelOrder(@RequestBody CancelOrder req) {
        log.info("START API /api/v1/order/cancelOrder");
        return ResponseEntity.status(HttpStatus.OK).body(service.orderService.cancelOrder(getCurrentUser(), req));
    }
    @PostMapping("/search")
    @Operation(summary = "Search Order")
    public ResponseEntity<Object> search(@RequestBody OrderSearch req) {
        log.info("start api /api/v1/order/search with payload: {}", JsonUtils.toJson(req));
        return ResponseEntity.ok(service.orderService.search(getCurrentUser(), req));
    }

    @Operation(summary = "API getDetail Order")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.orderDetailService.getOrderDetail(getCurrentUser(),id));
    }
}
