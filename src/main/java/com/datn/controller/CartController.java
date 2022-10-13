package com.datn.controller;

import com.datn.dto.customer.cart.request.CartRequest;
import com.datn.utils.base.PuddyController;
import com.datn.utils.base.rest.ResData;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController extends PuddyController {

    @ApiOperation(value = "API thêm sản phẩm vào giỏ hàng")
    @PostMapping("/add-to-cart")
    public ResponseEntity<ResData<String>> addToCart(@RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok(service.cartService.addToCart(getCurrentUser(), cartRequest));
    }

    @ApiOperation(value = "API hiển thị tất sản phẩm ở giỏ hàng")
    @GetMapping("/listCart")
    public ResponseEntity<?> getAllListCart(){
        return ResponseEntity.ok(service.cartService.getListCart(getCurrentUser()));
    }

    @ApiOperation(value = "API cập nhật số lượng sản phẩm trong giỏ hàng")
    @PostMapping("/update-cart")
    public ResponseEntity<?> updateCart(@RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok(service.cartService.updateCart(getCurrentUser(), cartRequest));
    }

    @ApiOperation(value = "API xóa sản phẩm trong giỏ hàng")
    @DeleteMapping("/delete/{productOptionId}")
    public ResponseEntity<?> deleteItemInCart(@PathVariable("productOptionId") String productOptionId) {
        return ResponseEntity.ok(service.cartService.deleteItemInCart(getCurrentUser(), productOptionId));
    }

    @ApiOperation(value = "Đếm số lượng sản phẩm trong giỏ hàng")
    @GetMapping("/countCartItem")
    public ResponseEntity<?> countCartItem() {
        return ResponseEntity.ok(service.cartService.countCartItem(getCurrentUser()));
    }


}
