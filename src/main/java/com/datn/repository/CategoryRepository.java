package com.datn.repository;

import com.datn.entity.Category;
import com.datn.repository.custom.CustomCategoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>,
        CustomCategoryRepository {
}
