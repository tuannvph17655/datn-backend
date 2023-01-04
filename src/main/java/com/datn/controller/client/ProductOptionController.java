package com.datn.controller.client;

import com.datn.dto.customer.product.productOption.ProductOptionIdReq;
import com.datn.utils.base.PuddyController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-option")
@RequiredArgsConstructor
@Slf4j
public class ProductOptionController extends PuddyController {

    @PostMapping("/findId")
    public ResponseEntity<?> findProductOptionId(@RequestBody ProductOptionIdReq req) {
        return ResponseEntity.ok(service.productOptionService.findProductOptionId(req));
    }


    @GetMapping("/findColor")
    public ResponseEntity<?> findColor(@RequestParam String sizeId, @RequestParam String productId) {
        return ResponseEntity.ok(service.productOptionService.findColorNameBySize(sizeId, productId));
    }

    @GetMapping("/findSize")
    public ResponseEntity<?> findSize(@RequestParam String productId) {
        return ResponseEntity.ok(service.productOptionService.findSizeByProductId(productId));
    }

}
