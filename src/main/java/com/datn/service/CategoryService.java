package com.datn.service;

import com.datn.dtos.response.CategoryResponse;
import com.datn.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> findAllCategory() {
        return categoryRepository.findAll()
                .stream().
                map(CategoryResponse::from)
                .collect(Collectors.toList());
    }
}
