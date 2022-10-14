package com.datn.service.impl;

import com.datn.dto.customer.address.AddressRes;
import com.datn.service.AddressService;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.constants.PuddyCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final PuddyRepository repository;

    @Override
    public Object getListAddress(CurrentUser currentUser) {
        List<AddressRes> res = repository.addressRepository.getListAddressByUserId(currentUser.getId());
        return new ResData<>(res, PuddyCode.OK);
    }
}
