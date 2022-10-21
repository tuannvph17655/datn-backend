package com.datn.service.impl;

import com.datn.dto.customer.cart.request.CartRequest;
import com.datn.dto.customer.cart.response.CartResponse;
import com.datn.dto.customer.cart.response.CountCartItem;
import com.datn.dto.customer.cart.response.ListCartResponse;
import com.datn.entity.CartEntity;
import com.datn.entity.ProductOptionEntity;
import com.datn.service.CartService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyConst;
import com.datn.utils.constants.enums.RoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartServiceImpl implements CartService {

    private final PuddyRepository repository;

    @Override
    public ResData<String> addToCart(CurrentUser currentUser, CartRequest cartRequest) {

        if (currentUser.getRole().equals(RoleEnum.ROLE_CUSTOMER)) {
            ProductOptionEntity productOptionEntity = repository.productOptionRepository.findById(cartRequest.getProductOptionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.PRODUCT_OPTION_VI.toLowerCase(Locale.ROOT))));

            if (productOptionEntity.getQty() < cartRequest.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.INVALID, PuddyConst.Nouns.QTY_VI));
            }

            Optional<CartEntity> cartOptional = repository.cartRepository.findByUserIdAndAndProductOptionId(currentUser.getId(), cartRequest.getProductOptionId());
            if (cartOptional.isPresent()) {

                CartEntity cart = cartOptional.get();
                int updateQuantity = cart.getQuantity() + cartRequest.getQuantity();

                cart.setQuantity(updateQuantity);

                repository.cartRepository.save(cart);

                return new ResData<>("Thêm vào giỏ hàng thành công !!!", PuddyCode.OK);
            } else {

                CartEntity newCart = new CartEntity();
                newCart.setId(UidUtils.generateUid());
                newCart.setUserId(currentUser.getId());
                newCart.setProductOptionId(productOptionEntity.getId());
                newCart.setQuantity(cartRequest.getQuantity());
                repository.cartRepository.save(newCart);

                return new ResData<>("Thêm vào giỏ hàng thành công !!", PuddyCode.OK);
            }

        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, PuddyConst.Messages.FORBIDDEN);
    }

    @Override
    //public ResData<List<CartResponse>> getListCart(CurrentUser currentUser) {
    public ResData<ListCartResponse> getListCart(CurrentUser currentUser) {
        if (currentUser.getRole().equals(RoleEnum.ROLE_CUSTOMER)) {
            List<CartResponse> cartList = repository.cartRepository.getListCart(currentUser.getId());

            Long totalPrice = cartList.stream().mapToLong(i -> i.getPrice() * i.getQuantity()).sum();
            Integer totalQuality = 0;
            for(int i =0 ; i < cartList.size(); i ++) {
                totalQuality += cartList.get(i).getQuantity().intValue();
            }
            ListCartResponse cartResponse = ListCartResponse.builder()
                    .carts(cartList)
                    .totalPrice(totalPrice)
                    .totalQuality(totalQuality)
                    .build();

            return new ResData<>(cartResponse, PuddyCode.OK);
        }

        throw new ResponseStatusException(HttpStatus.FORBIDDEN, PuddyConst.Messages.FORBIDDEN);
    }

    @Override
    public ResData<String> updateCart(CurrentUser currentUser, CartRequest cartRequest) {
        if (currentUser.getRole().equals(RoleEnum.ROLE_CUSTOMER)) {
            ProductOptionEntity productOptionEntity = repository.productOptionRepository.findById(cartRequest.getProductOptionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.PRODUCT_OPTION_VI.toLowerCase(Locale.ROOT))));

            if (productOptionEntity.getQty() < cartRequest.getQuantity()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.INVALID, PuddyConst.Nouns.QTY_VI));
            }

            Optional<CartEntity> cartOptional = repository.cartRepository.findByUserIdAndAndProductOptionId(currentUser.getId(), cartRequest.getProductOptionId());

            if (cartOptional.isPresent()) {
                CartEntity cart = cartOptional.get();
                int quantity = cartRequest.getQuantity();
                cart.setQuantity(quantity);
                repository.cartRepository.save(cart);
                return new ResData<>("Update quantity successfully !!!", PuddyCode.OK);
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, PuddyConst.Messages.FORBIDDEN);
    }

    @Override
    @Transactional
    public ResData<String> deleteItemInCart(CurrentUser currentUser, String productOptionId) {
        if (currentUser.getRole().equals(RoleEnum.ROLE_CUSTOMER)) {
            ProductOptionEntity productOptionEntity = repository.productOptionRepository.findById(productOptionId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.PRODUCT_OPTION_VI.toLowerCase(Locale.ROOT))));

            try {
                Optional<CartEntity> cartOptional = repository.cartRepository.findByUserIdAndAndProductOptionId(currentUser.getId(), productOptionId);
                if (cartOptional.isPresent()) {
                    repository.cartRepository.deleteByProductOptionId(productOptionId);
                    return new ResData<>("Đã xóa thành công !!!", PuddyCode.OK);
                }
            } catch (Exception e) {
                log.info(" ----------------- " + e);
            }
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, PuddyConst.Messages.FORBIDDEN);
    }

    @Override
    public ResData<CountCartItem> countCartItem(CurrentUser currentUser) {
        if (currentUser.getRole().equals(RoleEnum.ROLE_CUSTOMER)) {
            Integer itemCart = repository.cartRepository.countItemInCart(currentUser.getId());

            CountCartItem item = CountCartItem.builder()
                    .countItem(itemCart)
                    .build();

            return new ResData<>(item, PuddyCode.OK);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, PuddyConst.Messages.FORBIDDEN);
    }

    @Override
    public ResData<String> deleteAllCart(CurrentUser currentUser) {
        if (currentUser.getRole().equals(RoleEnum.ROLE_CUSTOMER)) {
            repository.cartRepository.clearCart(currentUser.getId());
            return new ResData<>("Đã xóa thành công !!!", PuddyCode.OK);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, PuddyConst.Messages.FORBIDDEN);
    }

}
