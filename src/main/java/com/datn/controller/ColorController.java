package com.datn.controller;

import com.datn.dtos.request.ColorRequest;
import com.datn.dtos.response.ColorResponse;
import com.datn.entity.Color;
import com.datn.service.ColorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/color")
public class ColorController {
    private final ColorService colorService;

    @GetMapping("/find-color")
    public ResponseEntity<?> findCategory(
            @RequestParam(name = "page" ,defaultValue = "0") int page,
            @RequestParam(name = "size" ,defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(colorService.findColor(page,size), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public  ResponseEntity<?> findAll() {
        return new ResponseEntity<>(colorService.findAllColor(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> creatColor(@Valid @RequestBody ColorRequest colorRequest) {
        return new ResponseEntity<>(colorService.createColor(colorRequest),HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findColorById(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(colorService.findColorById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateColor(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody ColorRequest colorRequest
    ) {
        colorService.updateColor(id,colorRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteColor(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(colorService.deleteColor(id), HttpStatus.OK);
    }
}
