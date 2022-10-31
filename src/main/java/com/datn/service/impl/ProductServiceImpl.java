package com.datn.service.impl;

import com.datn.dto.admin.product.ProductDetailResponse;
import com.datn.dto.admin.product_option.ProductOptionResponse;
import com.datn.dto.customer.product.ProductRelatedRes;
import com.datn.dto.customer.product.ProductReq;
import com.datn.dto.customer.product.ProductResponse;
import com.datn.entity.CategoryEntity;
import com.datn.entity.ColorEntity;
import com.datn.entity.MaterialEntity;
import com.datn.entity.ProductEntity;
import com.datn.entity.ProductOptionEntity;
import com.datn.entity.SizeEntity;
import com.datn.service.ProductService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.MoneyUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final PuddyRepository repository;


    @Override
    @Transactional
    public ResData<ProductDetailResponse> getProductDetail(String id) {

        ProductDetailResponse response = new ProductDetailResponse();

        ProductEntity product = repository.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.CATEGORY_VI)));

        response.setProductId(product.getId());
        response.setProductName(product.getName());
        response.setDescription(product.getDes());

        MaterialEntity material = repository.materialRepository.findById(product.getMaterialId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.MATERIAL_VI)));

        response.setMaterial(material.getName());

        CategoryEntity category = repository.categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Not found category with id = " + id));
        response.setCategoryName(category.getName());


        List<ProductOptionEntity> productOptions = repository.productOptionRepository.findByProductId(id);

        response.setProductOptions(
                productOptions.stream().map(option -> {

                    ProductOptionResponse productOptionResponse = new ProductOptionResponse();
                    productOptionResponse.setImage(option.getImage());

                    ColorEntity color = repository.colorRepository.findById(option.getColorId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.COLOR_VI)));

                    productOptionResponse.setColorName(color.getName());
                    productOptionResponse.setId(option.getId());
                    productOptionResponse.setQty(option.getQty());
                    productOptionResponse.setSizeId(option.getSizeId());

                    SizeEntity size = repository.sizeRepository.findById(option.getSizeId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.SIZE_VI)));

                    productOptionResponse.setSizeName(size.getName());
                    productOptionResponse.setImage(option.getImage());
                    productOptionResponse.setPrice(MoneyUtils.format(option.getPrice()));

                    return productOptionResponse;

                }).collect(Collectors.toList())
        );

        return new ResData<>(response, PuddyCode.OK);
    }

    @Override
    public PageData<ProductResponse> search(ProductReq req) {
        //Nếu là khách hàng thì không cho search theo trạng thái
        return repository.productCustomRepository.search(req);
    }

    @Override
    public Object searchV2(com.datn.dto.customer.product.search.ProductReq req) {
        return repository.productCustomRepository.searchV2(req);
    }

    @Override
    public ResData<List<ProductRelatedRes>> getRelatedProduct(String productId) {
        ProductEntity productRes = repository.productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format(PuddyConst.Messages.NOT_FOUND, PuddyConst.Nouns.CATEGORY_VI)));

        List<ProductRelatedRes> productRelated = repository.productRepository.findAllByCategoryId(productRes.getCategoryId())
                .stream().map(this::getListProductRelated).collect(Collectors.toList());
        return new ResData<>(productRelated, PuddyCode.OK);
    }

    private ProductRelatedRes getListProductRelated(ProductEntity product) {
        List<ProductOptionEntity> productOptions = repository.productOptionRepository.findByProductId(product.getId());
        String minPrice = productOptions.stream().min((o1, o2) -> o1.getPrice().compareTo(o2.getPrice())
        ).get().getPrice().toString();
        String maxPrice = productOptions.stream().max((o1, o2) -> o1.getPrice().compareTo(o2.getPrice())
        ).get().getPrice().toString();
        return ProductRelatedRes.builder()
                .productId(product.getId())
                .productName(product.getName())
                .image(productOptions.get(0).getImage())// chỉ dùng 1 link ảnh thôi . Trả về list ảnh ko biết cách show ra trong font-end :v
                .minPrice(minPrice)
                .maxPrice(maxPrice).build();
    }
}
