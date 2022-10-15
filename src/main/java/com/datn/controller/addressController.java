package com.datn.controller;

import com.datn.utils.base.PuddyController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
@Slf4j
public class addressController extends PuddyController {
    @GetMapping("/list-address")
    public ResponseEntity<?> getListAddress(){
        log.info("------ start api get list address -----");
        return ResponseEntity.ok(service.addressService.getListAddress(getCurrentUser()));
    }
}
