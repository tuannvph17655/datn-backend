package com.datn.utils.validator.suggest;

import com.datn.dto.customer.suggest.SuggestDto;
import com.datn.utils.base.PuddyException;
import com.datn.utils.common.StringUtils;
import com.datn.utils.constants.PuddyCode;

/**
 * @author myname
 */
public class SuggestValidator {
    public static void validGetSizeAvailable(SuggestDto dto) {
        if (StringUtils.isNullOrEmpty(dto.getCategoryId())) {
            throw new PuddyException(PuddyCode.BAD_REQUEST, "Không được để trống loại sản phẩm");
        }
        if (StringUtils.isNullOrEmpty(dto.getWeight())) {
            throw new PuddyException(PuddyCode.BAD_REQUEST, "Không được để trống cân nặng");
        }
        if (StringUtils.isNullOrEmpty(dto.getHeight())) {
            throw new PuddyException(PuddyCode.BAD_REQUEST, "Không được để trống chiều cao");
        }
        try {
            Long weight = Long.valueOf(dto.getWeight());
            if (weight < 41 || weight > 105) {
                throw new PuddyException(PuddyCode.BAD_REQUEST, "Cân nặng không hợp lệ");
            }
        } catch (Exception e) {
            throw new PuddyException(PuddyCode.BAD_REQUEST, "Cân nặng không hợp lệ");
        }
        try {
            Long height = Long.valueOf(dto.getHeight());
            if (height < 149 || height > 195) {
                throw new PuddyException(PuddyCode.BAD_REQUEST, "Chiều cao không hợp lệ");
            }
        } catch (Exception e) {
            throw new PuddyException(PuddyCode.BAD_REQUEST, "Chiều cao không hợp lệ");
        }
    }
}
