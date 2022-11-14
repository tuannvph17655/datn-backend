package com.datn.controller.admin;

import com.datn.dto.customer.product.productOption.ProductOptionReq;
import com.datn.dto.customer.product.productOption.ProductOptionRes;
import com.datn.service.ProductOptionService;
import com.datn.utils.base.PuddyController;
import com.datn.utils.base.rest.PageData;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/admin/productOption")
public class AdminProductOptionController extends PuddyController {
    private final ProductOptionService productOptionService;

    @PostMapping("/findByProductId")
    public ResponseEntity<PageData<ProductOptionRes>> getAllByProductId(@RequestBody ProductOptionReq productOptionReq) {
        return ResponseEntity.ok().body(productOptionService.findAllByProductId(getCurrentUser(), productOptionReq));
    }

}
