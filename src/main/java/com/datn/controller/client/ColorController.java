package com.datn.controller.client;

import com.datn.utils.base.PuddyController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/color")
@RequiredArgsConstructor
@Slf4j
public class ColorController extends PuddyController {
    @GetMapping("")
    public ResponseEntity<?> getAllColor() {
        log.info("START API /api/v1/color");
        return ResponseEntity.status(HttpStatus.OK).body(service.colorService.getListColor());
    }

}
