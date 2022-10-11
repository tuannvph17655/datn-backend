package com.datn.utils.base;

import com.datn.repository.CategoryRepository;
import com.datn.repository.ProductOptionRepository;
import com.datn.repository.ProductRepository;
import com.datn.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PuddyRepository {
//    public final AddressRepository addressRepository;
    public final CategoryRepository categoryRepository;
    public final ProductRepository productRepository;
    public final UserRepository userRepository;
    public final ProductOptionRepository productOptionRepository;
}
