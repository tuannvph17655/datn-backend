package com.datn.service.impl;

import com.datn.dto.customer.product.search.ProductDto;
import com.datn.service.ProductInfoService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.validator.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {
    private final PuddyRepository repository;

    @Override
    public Object noPage(CurrentUser currentUser) {
        AuthValidator.checkLogin(currentUser);
        return ResData.ok(repository.productRepository.findAllOrderByName()
                .stream().map(o -> ProductDto.builder()
                        .id(o.getId())
                        .name(o.getName())
                        .build()).collect(Collectors.toList()));
    }
}
