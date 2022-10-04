package com.datn.controller;

import com.datn.dtos.request.CategoryRequest;
import com.datn.dtos.response.CategoryResponse;
import com.datn.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllCategory(
            @RequestParam(name = "page" ,defaultValue = "0") int page,
            @RequestParam(name = "size" ,defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(categoryService.findAllCategory(page,size), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> creatCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryService.createCategory(categoryRequest),HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findCategoryById(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(categoryService.findCategoryById(id),HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }




}
