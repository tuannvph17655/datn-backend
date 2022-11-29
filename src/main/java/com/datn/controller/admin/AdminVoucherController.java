package com.datn.controller.admin;

import com.datn.utils.base.PuddyController;
import com.datn.utils.base.enum_dto.DiscountDto;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/voucher")
@RequiredArgsConstructor
@Slf4j
public class AdminVoucherController extends PuddyController {
	@GetMapping("")
	public ResponseEntity<?> getAllDiscount(){
		log.info("START API /api/v1/discount");
		return ResponseEntity.status(HttpStatus.OK).body(service.voucherService.getListVoucher());
	}

	@PostMapping("/create")
	public ResponseEntity<ResData<String>> create(@RequestBody DiscountDto dto){
		log.info("Start api create with dto: {}", JsonUtils.toJson(dto));
		return ResponseEntity.status(HttpStatus.OK).body(service.voucherService.create(dto));
	}

	@PostMapping("/update")
	public ResponseEntity<ResData<String>> update(@RequestBody DiscountDto dto){
		log.info("Start api create with dto: {}", JsonUtils.toJson(dto));
		return ResponseEntity.status(HttpStatus.OK).body(service.voucherService.update(dto));
	}
}
