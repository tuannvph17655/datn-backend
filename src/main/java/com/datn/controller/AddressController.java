package com.datn.controller;

import com.datn.dto.customer.address.AddressReq;
import com.datn.utils.base.PuddyController;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
@Slf4j
public class AddressController extends PuddyController {
    @GetMapping("/list-address")
    public ResponseEntity<?> getListAddress() {
        log.info("------ start api get list address -----");
        return ResponseEntity.ok(service.addressService.getListAddress(getCurrentUser()));
    }

    @GetMapping("/get-default")
    public ResponseEntity<?> getAddressDefault(){
        log.info("------ start api get address default -----");
        return ResponseEntity.ok(service.addressService.getAddressDefault(getCurrentUser()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "API get address detail")
    public ResponseEntity<?> getAddressById(@PathVariable("id") String id) {
        log.info("------ start api get address byId -----");
        return ResponseEntity.ok(service.addressService.getAddressById(getCurrentUser(), id));
    }

    @PostMapping("/create")
    @ApiOperation(value = "API create address")
    public ResponseEntity<?> createNewAddress(@RequestBody AddressReq req) {
        log.info("------ start api create address -----");
        return ResponseEntity.ok(service.addressService.createAddress(getCurrentUser(), req));
    }

    @PostMapping("/update")
    @ApiOperation(value = "API update address")
    public ResponseEntity<?> updateAddress(@RequestBody AddressReq req) {
        log.info("------ start api update address -----");
        return ResponseEntity.ok(service.addressService.updateAddress(getCurrentUser(), req));
    }

    @PutMapping("/delete/{id}")
    @ApiOperation(value = "API delete address")
    public ResponseEntity<?> deleteAddress(@PathVariable("id") String id) {
        log.info("------ start api delete address -----");
        service.addressService.deActiveAddress(getCurrentUser(), id);
        return ResponseEntity.ok("Delete address successfully !!");
    }

    @GetMapping("/default/{id}")
    @ApiOperation(value = "API setDefault address")
    public ResponseEntity<?> setAddressDefault(@PathVariable("id") String id) {
        service.addressService.setAddressDefault(getCurrentUser(), id);
        return ResponseEntity.ok("Set default address successfully !");
    }


}
