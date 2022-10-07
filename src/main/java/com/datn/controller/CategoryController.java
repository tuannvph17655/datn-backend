package com.datn.controller;

import com.datn.dtos.request.CategoryRequest;
import com.datn.dtos.response.CategoryResponse;
import com.datn.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/find-category")
    public ResponseEntity<?> findCategory(
            @RequestParam(name = "page" ,defaultValue = "0") int page,
            @RequestParam(name = "size" ,defaultValue = "10") int size
//            @RequestParam(name = "name" ,required = false) String name,
//            @RequestParam(name = "active",required = false) boolean active
    ) {
        return new ResponseEntity<>(categoryService.findCategory(page,size), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public  ResponseEntity<?> findAll() {
        return new ResponseEntity<>(categoryService.findAllCategory(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> creatCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody CategoryRequest categoryRequest
            ) {
        categoryService.updateCategory(id,categoryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
