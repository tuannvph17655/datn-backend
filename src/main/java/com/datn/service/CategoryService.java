package com.datn.service;

import com.datn.dtos.request.CategoryRequest;
import com.datn.dtos.response.CategoryResponse;
import com.datn.entity.Category;
import com.datn.exception.ServiceException;
import com.datn.repository.CategoryRepository;
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

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<CategoryResponse> findCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Category> categories = categoryRepository.findAll(pageable);
        List<CategoryResponse> categoryResponses = categories.stream().map(CategoryResponse::from).collect(Collectors.toList());
        return new PageImpl<>(categoryResponses,pageable,categories.getTotalElements());
    }

    public List<CategoryResponse> findAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList());
    }

    public Category createCategory(CategoryRequest categoryRequests) {
        Category category = Category.from(categoryRequests);
            return categoryRepository.save(category);
    }

    public CategoryResponse findCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ServiceException(HttpStatus.BAD_REQUEST,CATEGORY_NOT_FOUND));
        return CategoryResponse.from(category);
    }

    public CategoryResponse deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()->new ServiceException(HttpStatus.BAD_REQUEST, CATEGORY_NOT_FOUND)
        );
        category.setActive(false);
        categoryRepository.save(category);
        return CategoryResponse.from(category);
    }


}
