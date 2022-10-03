package com.datn.service;

import com.datn.dtos.response.ProductResponse;
import com.datn.entity.Product;
import com.datn.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductResponse> findProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductResponse> responses = products.stream().map(ProductResponse::of).collect(Collectors.toList());
        return new PageImpl<>(responses, pageable, products.getTotalElements());
    }
}
