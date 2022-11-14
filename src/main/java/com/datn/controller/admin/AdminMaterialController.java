package com.datn.controller.admin;
import com.datn.dto.admin.material.MaterialReq;
import com.datn.dto.admin.material.MaterialRes;
import com.datn.utils.base.PuddyController;
import com.datn.utils.base.enum_dto.MaterialDto;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/material")
@RequiredArgsConstructor
@Slf4j
public class AdminMaterialController extends PuddyController {

    @PostMapping("/create")
    @Operation(summary = "API thêm mới chất liệu sp")
    public ResponseEntity<ResData<String>> create(@RequestBody MaterialDto dto){
        log.info("start api create with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.materialService.create(getCurrentUser(), dto));
    }

    @PostMapping("/update")
    @Operation(summary = "API sửa chất liệu sp")
    public ResponseEntity<ResData<String>> update(@RequestBody MaterialDto dto){
        log.info("start api update with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.materialService.update(getCurrentUser(), dto));
    }

    @PostMapping("/delete")
    @Operation(summary = "API xóa chất liệu sp")
    public ResponseEntity<ResData<String>> delete(@RequestBody MaterialDto dto){
        log.info("start api delele with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.materialService.delete(getCurrentUser(), dto));
    }

    @PostMapping("/search")
    @Operation(summary = "API lấy ra danh sách chất liệu và filter")
    public ResponseEntity<PageData<MaterialRes>> search(@RequestBody MaterialReq materialReq) {
        return ResponseEntity.status(HttpStatus.OK).body(service.materialService.search(getCurrentUser(),materialReq));
    }

    @GetMapping()
    @Operation(summary = "Lấy ra tất cả")
    public ResponseEntity<ResData<List<MaterialRes>>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.materialService.getAll(getCurrentUser()));
    }

}
