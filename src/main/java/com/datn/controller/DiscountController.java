package com.datn.controller;

import com.datn.utils.base.PuddyController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/discount")
@RequiredArgsConstructor
@Slf4j
public class DiscountController extends PuddyController {
	@GetMapping("")
	public ResponseEntity<?> getAllDiscount(){
		log.info("START API /api/v1/discount");
		return ResponseEntity.status(HttpStatus.OK).body(service.discountService.getListDiscount());
	}
}
