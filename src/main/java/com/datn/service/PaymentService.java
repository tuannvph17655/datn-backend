package com.datn.service;

import com.datn.dtos.request.PaymentRequest;
import com.datn.dtos.response.PaymentResponse;
import com.datn.entity.Payment;
import com.datn.exception.ServiceException;
import com.datn.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.datn.message.ResponseMessageText.CATEGORY_NOT_FOUND;
import static com.datn.message.ResponseMessageText.PAYMENT_NOT_FOUND;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Page<PaymentResponse> findPayment(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Payment> payments = paymentRepository.findAll(pageable);
        List<PaymentResponse> paymentResponses = payments.stream()
                                                .map(PaymentResponse::from)
                                                .collect(Collectors.toList());
        return new PageImpl<>(paymentResponses,pageable, paymentResponses.size());
    }

    public List<PaymentResponse> findAllPayment() {
        return paymentRepository.findAll()
                .stream()
                .map(PaymentResponse::from)
                .collect(Collectors.toList());
    }

    public void createPayment(PaymentRequest paymentRequest) {
        Payment payment = Payment.from(paymentRequest);
        paymentRepository.save(payment);
    }

    public PaymentResponse findPaymentById(Long id) {
        Payment category = paymentRepository.findById(id).orElseThrow(
                () -> new ServiceException(HttpStatus.BAD_REQUEST,PAYMENT_NOT_FOUND));
        return PaymentResponse.from(category);
    }

    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                ()->new ServiceException(HttpStatus.BAD_REQUEST, PAYMENT_NOT_FOUND)
        );
        payment.setActive(false);
        paymentRepository.save(payment);
    }

    public void updatePayment(Long id, PaymentRequest paymentRequest) {
        Payment payment = paymentRepository.findById(id).
                orElseThrow(()->new ServiceException(
                        HttpStatus.BAD_REQUEST, PAYMENT_NOT_FOUND))
                    .from(paymentRequest);
        paymentRepository.save(payment);
    }

}
