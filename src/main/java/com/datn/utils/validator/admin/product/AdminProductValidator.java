package com.datn.utils.validator.admin.product;


import com.datn.dto.admin.product.create_update.OptionDto;
import com.datn.dto.admin.product.create_update.ProductDto;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.ValidatorUtils;
import com.datn.utils.constants.PuddyCode;

import java.util.Collections;
import java.util.List;

/**
 * @author myname
 */
public class AdminProductValidator {

    private static final String NAME = "Tên";
    private static final String DES = "Mô tả";
    private static final String OPTION = "Tùy chọn";
    private static final String PRICE = "Giá";
    private static final String QTY = "Số lượng";

    private AdminProductValidator() {
        super();
    }

    public static void validCreate(ProductDto dto) {
        PuddyRepository repository = BeanUtils.getBean(PuddyRepository.class);
        ValidatorUtils.validNullOrEmpty(NAME, dto.getName());
        ValidatorUtils.validOnlyCharacterAndNumber(NAME, dto.getName());
        ValidatorUtils.validLength(NAME, dto.getName(), 6, 100);
        ValidatorUtils.validNullOrEmpty(DES, dto.getDes());
        ValidatorUtils.validOnlyCharacterAndNumber(DES, dto.getDes());
        ValidatorUtils.validLength(DES, dto.getDes(), 6, 255);
//        ValidatorUtils.validNullOrEmptyList(OPTION, Collections.singletonList(dto.getOptions()));
//        validOptionDto(dto.getOptions(), repository);
        validExist(dto, repository);
    }

    private static void validExist(ProductDto dto, PuddyRepository repository) {
        if (!repository.categoryRepository.existsByIdAndActive(dto.getCategoryId(), true)) {
            throw new PuddyException(PuddyCode.INTERNAL_SERVER, "Danh mục không hợp lệ");
        }
        if (!repository.materialRepository.existsByIdAndActive(dto.getMaterialId(), true)) {
            throw new PuddyException(PuddyCode.INTERNAL_SERVER, "Chất liệu không hợp lệ");
        }

    }

    private static void validOptionDto(List<OptionDto> options, PuddyRepository repository) {
        for (OptionDto option : options) {
            ValidatorUtils.validNullOrEmpty(PRICE, option.getPrice());
            ValidatorUtils.validOnlyNumber(PRICE, option.getPrice());
            ValidatorUtils.validPrice(PRICE, option.getPrice());
            ValidatorUtils.validNullOrEmpty(QTY, option.getQty());
            ValidatorUtils.validOnlyNumber(QTY, option.getQty());
            ValidatorUtils.validPrice(QTY, option.getQty());
            validExist(option, repository);
        }
    }

    private static void validExist(OptionDto option, PuddyRepository repository) {
        if (!repository.sizeRepository.existsById(option.getSizeId())) {
            throw new PuddyException(PuddyCode.INTERNAL_SERVER, "Size không hợp lệ");
        }
        if (!repository.colorRepository.existsByIdAndActive(option.getColorId(), true)) {
            throw new PuddyException(PuddyCode.INTERNAL_SERVER, "Màu không hợp lệ");
        }
    }
}
