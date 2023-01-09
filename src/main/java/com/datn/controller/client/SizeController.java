package com.datn.controller.client;

import com.datn.dto.customer.size.response.SizeResponse;
import com.datn.utils.base.PuddyController;
import com.datn.utils.base.rest.ResData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/size")
@RequiredArgsConstructor
@Slf4j
public class SizeController extends PuddyController {
    @GetMapping("/getAll")
    public ResponseEntity<ResData<List<SizeResponse>>> getAllColor4Search() {
        log.info("START API /api/v1/size");
        return ResponseEntity.status(HttpStatus.OK).body(service.sizeService.getAllSize());
    }

}
