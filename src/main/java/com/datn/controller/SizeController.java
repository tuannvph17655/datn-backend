package com.datn.controller;

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
        return new ResponseEntity<>(sizeService.createSize(sizeRequest),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateSize(
            @PathVariable(name = "id") Long id ,
            @Valid @RequestBody SizeRequest sizeRequest){
        return new ResponseEntity<>(sizeService.updateSize(id, sizeRequest), HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteSize(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(sizeService.deleteSize(id), HttpStatus.OK);
    }

}
