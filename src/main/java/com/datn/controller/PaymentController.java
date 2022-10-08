package com.datn.controller;

import com.datn.dtos.request.PaymentRequest;
import com.datn.service.PaymentService;
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
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/find-payment")
    public ResponseEntity<?> findPayment(
            @RequestParam(name = "page" ,defaultValue = "0") int page,
            @RequestParam(name = "size" ,defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(paymentService.findPayment(page,size), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public  ResponseEntity<?> findAll() {
        return new ResponseEntity<>(paymentService.findAllPayment(), HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findPaymentById(
            @PathVariable(name = "id") Long id
    ) {
        return new ResponseEntity<>(paymentService.findPaymentById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> creatPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        paymentService.createPayment(paymentRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePayment(
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody PaymentRequest paymentRequest
    ) {
        paymentService.updatePayment(id,paymentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deletePayment(
            @PathVariable(name = "id") Long id
    ) {
        paymentService.deletePayment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
