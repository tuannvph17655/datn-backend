package com.datn.controller.admin;

import com.datn.utils.base.PuddyController;
import com.datn.utils.base.enum_dto.SizeDto;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/size")
@RequiredArgsConstructor
@Slf4j
public class AdminSizeController extends PuddyController {
    @PostMapping("/create")
    @Operation(summary = "API thêm mới size sp")
    public ResponseEntity<ResData<String>> create(@RequestBody SizeDto dto) {
        log.info("start api create with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.sizeService.create(getCurrentUser(), dto));
    }

    @PostMapping("/update")
    @Operation(summary = "API cập nhật size sp")
    public ResponseEntity<ResData<String>> update(@RequestBody SizeDto dto){
        log.info("start api update with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.sizeService.update(getCurrentUser(), dto));
    }

    @PostMapping("/delete")
    @Operation(summary = "API xóa size sp")
    public ResponseEntity<ResData<String>> delete(@RequestBody SizeDto dto){
        log.info("start api delete with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.sizeService.delete(getCurrentUser(), dto));
    }
}
