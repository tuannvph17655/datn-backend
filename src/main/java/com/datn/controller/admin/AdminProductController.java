package com.datn.controller.admin;


import com.datn.dto.admin.product.create_update.ProductDto;
import com.datn.dto.customer.product.ProductReq;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/product")
@RequiredArgsConstructor
@Slf4j
public class AdminProductController extends PuddyController {

    @Operation(summary = "API search product cho admin")
    @PostMapping("/search")
    public ResponseEntity<Object> search4Admin(@RequestBody ProductReq req) {
        log.info("start api /api/v1/product/admin/search with req: {}", JsonUtils.toJson(req));
        return ResponseEntity.ok(service.adminProductService.search(getCurrentUser(), req));
    }

    @Operation(summary = "API detail product cho admin")
    @GetMapping("/detail/{id}")
    public ResponseEntity<Object> detail4Admin(@PathVariable String id) {
        log.info("start api /api/v1/product/admin/detail with id: {}", id);
        return ResponseEntity.ok(service.adminProductService.detail(getCurrentUser(), id));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ProductDto dto) {
        return ResponseEntity.ok(service.adminProductService.create(getCurrentUser(), dto));
    }
}
