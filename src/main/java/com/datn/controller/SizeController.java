package com.datn.controller;

import com.datn.dtos.request.CategoryRequest;
import com.datn.dtos.request.SizeRequest;
import com.datn.service.SizeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/size")
public class SizeController {

    private final SizeService sizeService;

    @GetMapping("/find-size")
    public ResponseEntity<?> findSize(
            @RequestParam(name = "page" ,defaultValue = "0") int page,
            @RequestParam(name = "size" ,defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(sizeService.findSize(page,size), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public  ResponseEntity<?> findAll() {
        return new ResponseEntity<>(sizeService.findAllSize(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> creatSize(@Valid @RequestBody SizeRequest sizeRequest) {
        sizeService.createSize(sizeRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSize(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody SizeRequest sizeRequest
    ) {
        sizeService.updateSize(id,sizeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteSize(
            @PathVariable(name = "id") Long id
    ) {
        sizeService.deleteSize(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}