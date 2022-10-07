package com.datn.repository.custom;

import com.datn.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomCategoryRepository {

    Page<Category> findCategoryBy(Pageable pageable, String name);
}
