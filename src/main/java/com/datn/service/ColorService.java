package com.datn.service;


import com.datn.dto.customer.product.ColorResponse;
import com.datn.utils.base.rest.ResData;

import java.util.List;

public interface ColorService {
    ResData<List<ColorResponse>> getListColor();

}
