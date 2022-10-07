package com.datn.repository.impl;

import com.datn.entity.Category;
import com.datn.repository.custom.CustomCategoryRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {
    @Autowired
    private EntityManager entityManager;
    @Override
    public Page<Category> findCategoryBy(Pageable pageable , String name) {
        String sql = "SELECT * FROM categories Where true ";

        List<Pair<String,Object>> bindings = new ArrayList<>();
        if(StringUtils.isNotBlank(name)) {
            sql += "AND name =: name ";
            bindings.add(Pair.of("name",name));
        }

        Query query = entityManager.createNativeQuery(sql, Category.class)
                .setMaxResults(pageable.getPageSize())
                .setFirstResult((int) pageable.getOffset());
        for(Pair<String, Object> binding : bindings) {
            query.setParameter(binding.getLeft(),binding.getRight());
        }
        List<Category> categoryList = query.getResultList();
        Page<Category> categoryPage = new PageImpl<>(categoryList,pageable,categoryList.size());
        return categoryPage;
    }
}
