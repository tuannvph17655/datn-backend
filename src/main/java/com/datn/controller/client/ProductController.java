package com.datn.controller.client;

import com.datn.dto.admin.product.ProductDetailResponse;
import com.datn.dto.customer.product.ProductRelatedReq;
import com.datn.dto.customer.product.ProductReq;
import com.datn.utils.base.PuddyController;
import com.datn.utils.base.rest.ResData;
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
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController extends PuddyController {

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody ProductReq req) {
        return ResponseEntity.ok(service.productService.search(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResData<ProductDetailResponse>> getProductDetail(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.productService.getProductDetail(id));
    }

    @PostMapping("/search/v2")
    public ResponseEntity<?> searchV2(@RequestBody com.datn.dto.customer.product.search.ProductReq req) {
        return ResponseEntity.ok(service.productService.searchV2(req));
    }

    @PostMapping("/relatedProduct")
    public ResponseEntity<?> getRelatedProduct(@RequestBody ProductRelatedReq req) {
        return ResponseEntity.ok(service.productService.getRelatedProduct(req.getProductId()));
    }

    @GetMapping("/no-page")
    public ResponseEntity<Object> productNoPage() {
        return ResponseEntity.ok(service.productInfoService.noPage(getCurrentUser()));
    }
}
