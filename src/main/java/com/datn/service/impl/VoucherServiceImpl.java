package com.datn.service.impl;

import com.datn.dto.admin.voucher.VoucherDto;
import com.datn.dto.admin.voucher.VoucherReq;
import com.datn.dto.customer.discount.VoucherResponse;
import com.datn.service.VoucherService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.PageData;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.validator.auth.AuthValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class VoucherServiceImpl implements VoucherService {

    private final PuddyRepository repository;
    @Transactional
    @Override
    public PageData<VoucherResponse> search(CurrentUser currentUser, VoucherReq req) {
        return null;
    }

    @Override
    public ResData<String> create(CurrentUser currentUser, VoucherDto dto) {
        AuthValidator.checkAdmin(currentUser);


        return null;
    }

    @Override
    public ResData<String> update(CurrentUser currentUser, VoucherDto dto) {
        return null;
    }

    @Override
    public ResData<String> delete(CurrentUser currentUser, String id) {
        return null;
    }
}
