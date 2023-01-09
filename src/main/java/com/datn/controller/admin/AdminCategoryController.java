package com.datn.controller.admin;


import com.datn.dto.admin.category.CategoryDto;
import com.datn.dto.admin.category.CategoryReq;
import com.datn.dto.admin.category.CategoryRes;
import com.datn.dto.admin.category.CategoryRes4Admin;
import com.datn.utils.base.PuddyController;
import com.datn.utils.base.rest.PageData;
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
@RequestMapping("/api/v1/admin/category")
@RequiredArgsConstructor
@Slf4j
public class AdminCategoryController extends PuddyController {

    @PostMapping("/search")
    public ResponseEntity<PageData<CategoryRes>> search(@RequestBody CategoryReq req) {
        log.info("start api search with req: {}", JsonUtils.toJson(req));
        return ResponseEntity.ok(service.categoryService.search(getCurrentUser(), req));
    }

    @PostMapping("/create")
    public ResponseEntity<ResData<String>> create(@RequestBody CategoryDto dto) {
        log.info("start api create with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.categoryService.create(getCurrentUser(), dto));
    }

    @PostMapping("/delete")
    public ResponseEntity<ResData<String>> delete(@RequestBody CategoryDto dto) {
        log.info("start api delete with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.categoryService.delete(getCurrentUser(), dto));
    }

    @PostMapping("/update")
    public ResponseEntity<ResData<String>> update(@RequestBody CategoryDto dto) {
        log.info("start api update with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.categoryService.update(getCurrentUser(), dto));
    }

    @PostMapping("/detail")
    public ResponseEntity<ResData<CategoryRes>> detail(@RequestBody CategoryDto dto) {
        log.info("start api detail with dto: {}", JsonUtils.toJson(dto));
        return ResponseEntity.status(HttpStatus.OK).body(service.categoryService.detail(getCurrentUser(), dto));
    }
}
