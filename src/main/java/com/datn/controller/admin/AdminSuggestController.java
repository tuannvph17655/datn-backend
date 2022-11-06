package com.datn.controller.admin;

import com.datn.dto.customer.suggest.SuggestDto;
import com.datn.utils.base.PuddyController;
import com.datn.utils.base.enum_dto.ColorDto;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/suggest")
@RequiredArgsConstructor
@Slf4j
public class AdminSuggestController extends PuddyController {
    @PostMapping("/create")
    @Operation(summary = "API thêm mới gợi ý")
    public ResponseEntity<ResData<String>> create(@RequestBody SuggestDto dto) {
        log.info("start api create with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.suggestService.create(getCurrentUser(), dto));
    }

    @PostMapping("/update")
    @Operation(summary = "API cập nhật gợi ý")
    public ResponseEntity<ResData<String>> update(@RequestBody SuggestDto dto){
        log.info("start api update with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.suggestService.update(getCurrentUser(), dto));
    }

}
