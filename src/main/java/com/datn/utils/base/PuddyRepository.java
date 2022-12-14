package com.datn.utils.base;

import com.datn.repository.*;
import com.datn.repository.custom.ProductCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PuddyRepository {
    public final AddressRepository addressRepository;
    public final CategoryRepository categoryRepository;
    public final ProductRepository productRepository;
    public final UserRepository userRepository;
    public final ProductOptionRepository productOptionRepository;
    public final ProductCustomRepository productCustomRepository;
    public final SizeRepository sizeRepository;
    public final ColorRepository colorRepository;
    public final MaterialRepository materialRepository;
    public final CartRepository cartRepository;
    public final OrderRepository orderRepository;
    public final SuggestRepository suggestRepository;
    public final BodyHeightRepository bodyHeightRepository;
    public final BodyWeightRepository bodyWeightRepository;
    public final OrderDetailRepository orderDetailRepository;
    public final OrderStatusRepository orderStatusRepository;
    public final VoucherRepository voucherRepository;

}
