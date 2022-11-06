package com.datn.controller.admin;

import com.datn.dto.admin.order.change_status.ChangeStatusDto;
import com.datn.dto.admin.order.search.ListOrderRequest;
import com.datn.dto.customer.order.CancelOrder;
import com.datn.utils.base.PuddyController;
import com.datn.utils.common.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/order")
@RequiredArgsConstructor
@Slf4j
public class AdminOrderController extends PuddyController {

    @Operation(summary = "My Orders")
    @GetMapping("/listOrder")
    public ResponseEntity<?> getMyOrder(ListOrderRequest request) {
        log.info("START API /api/v1/admin/order/listOrder");
        return ResponseEntity.ok(service.orderService.getMyOrder4Admin(getCurrentUser(), request));
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "API chi tiet hoa don")
    public ResponseEntity<Object> detail4Admin(@PathVariable String id) {
        return ResponseEntity.ok(service.orderService.detail(getCurrentUser(), id));
    }

    @PostMapping("/change-status")
    @Operation(summary = "API thay đổi trạng thái đơn hàng")
    public ResponseEntity<Object> changeStatus4Admin(@RequestBody ChangeStatusDto dto) {
        log.info("START API /api/v1/order/admin/change-status with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.ok(service.orderService.changeStatus(getCurrentUser(), dto));
    }

    @PostMapping("/cancel-order")
    public ResponseEntity<Object> cancelOrder4Admin(@RequestBody CancelOrder cancelOrder) {
        return ResponseEntity.ok(service.orderService.cancelOrder(getCurrentUser(),cancelOrder));
    }

    @PostMapping("/reject-order")
    public ResponseEntity<Object> rejectlOrder4Admin(@RequestBody CancelOrder cancelOrder) {
        return ResponseEntity.ok(service.orderService.rejectOrder(getCurrentUser(),cancelOrder));
    }

}
