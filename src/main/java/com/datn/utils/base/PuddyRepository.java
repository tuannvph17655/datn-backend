package com.datn.utils.base;

import com.datn.repository.CartRepository;
import com.datn.repository.CategoryRepository;
import com.datn.repository.ColorRepository;
import com.datn.repository.MaterialRepository;
import com.datn.repository.ProductOptionRepository;
import com.datn.repository.ProductRepository;
import com.datn.repository.SizeRepository;
import com.datn.repository.UserRepository;
import com.datn.repository.custom.ProductCustomRepository;
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
    public final ProductCustomRepository productCustomRepository;
    public final SizeRepository sizeRepository;
    public final ColorRepository colorRepository;
    public final MaterialRepository materialRepository;
    public final CartRepository cartRepository;
}
