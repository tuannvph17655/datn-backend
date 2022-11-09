package com.datn.controller.admin;

import com.datn.dto.admin.color.ColorReq;
import com.datn.dto.admin.color.ColorRes;
import com.datn.utils.base.PuddyController;
import com.datn.utils.base.enum_dto.ColorDto;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/color")
@RequiredArgsConstructor
@Slf4j
public class AdminColorController extends PuddyController {


    @PostMapping("/create")
    @Operation(summary = "API thêm mới màu sp")
    public ResponseEntity<ResData<String>> create(@RequestBody ColorDto dto) {
        log.info("start api create with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.colorService.create(getCurrentUser(), dto));
    }

    @PostMapping("/update")
    @Operation(summary = "API cập nhật màu sp")
    public ResponseEntity<ResData<String>> update(@RequestBody ColorDto dto){
        log.info("start api update with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.colorService.update(getCurrentUser(), dto));
    }

    @PostMapping("/delete")
    @Operation(summary = "API xóa màu sp")
    public ResponseEntity<ResData<String>> delete(@RequestBody ColorDto dto){
        log.info("start api delete with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.colorService.delete(getCurrentUser(), dto));
    }

    @PostMapping("/search")
    @Operation(summary = "API lấy ra danh sách sản phẩm và filter sản phẩm")
    public ResponseEntity<PageData<ColorRes>> search(@RequestBody ColorReq colorReq) {
        return ResponseEntity.status(HttpStatus.OK).body(service.colorService.getListColor4admin(getCurrentUser(),colorReq));
    }
}
