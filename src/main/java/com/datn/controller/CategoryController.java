package com.datn.controller;

import com.datn.dtos.response.CategoryResponse;
import com.datn.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/find-all")
    public ResponseEntity<List<CategoryResponse>> findAllCategory(
    ) {
        return new ResponseEntity<>(categoryService.findAllCategory(), HttpStatus.OK);
    }

}
