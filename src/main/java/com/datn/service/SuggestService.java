package com.datn.service;


import com.datn.dto.admin.category.CategoryDto;
import com.datn.dto.customer.suggest.SuggestDto;

import java.util.List;

public interface SuggestService {
    List<CategoryDto> getCategories();

    Object getSizeAvailable(SuggestDto dto);
}
