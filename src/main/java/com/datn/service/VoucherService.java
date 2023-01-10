package com.datn.service;

import com.datn.dto.admin.voucher.VoucherDto;
import com.datn.dto.admin.voucher.VoucherReq;
import com.datn.dto.customer.discount.VoucherResponse;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;

public interface VoucherService {
    PageData<VoucherResponse> search(CurrentUser currentUser,VoucherReq req);
    ResData<String> create(CurrentUser currentUser, VoucherDto dto);
    ResData<String> update(CurrentUser currentUser, VoucherDto dto);
    ResData<String> delete(CurrentUser currentUser, String id);

}
