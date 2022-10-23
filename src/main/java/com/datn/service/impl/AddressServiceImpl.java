package com.datn.service.impl;

import com.datn.dto.customer.address.AddressReq;
import com.datn.dto.customer.address.AddressRes;
import com.datn.entity.AddressEntity;
import com.datn.service.AddressService;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.base.rest.ResData;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.UidUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.validator.address.AddressValidator;
import com.datn.utils.validator.auth.AuthValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import java.util.Date;
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

    @Override
    public Object createAddress(CurrentUser currentUser, AddressReq req) {
        log.info("----- Address create start ------");
        AuthValidator.checkCustomer(currentUser);
        try {
            List<AddressRes> res = repository.addressRepository.getListAddressByUserId(currentUser.getId());
            if (res.isEmpty()) {
                AddressValidator.validCreateAddress(req);
                AddressEntity builder = AddressEntity.builder()
                        .id(UidUtils.generateUid())
                        .userId(currentUser.getId())
                        .nameOfRecipient(req.getNameOfRecipient().trim())
                        .phoneNumber(req.getPhoneNumber().trim())
                        .addressDetail(req.getAddressDetail().trim())
                        .wardCode(req.getWardCode().trim())
                        .wardName(req.getWardName().trim())
                        .districtId(req.getDistrictId().trim())
                        .districtName(req.getDistrictName().trim())
                        .provinceId(req.getProvinceId().trim())
                        .provinceName(req.getProvinceName().trim())
                        .isDefault(Boolean.FALSE)
                        .active(Boolean.TRUE)
                        .createdBy(currentUser.getId())
                        .createdDate(new Date())
                        .build();
                repository.addressRepository.save(builder);
                return new ResData<>(builder.getId(), PuddyCode.CREATED);
            } else {
                AddressEntity builder = AddressEntity.builder()
                        .id(UidUtils.generateUid())
                        .userId(currentUser.getId())
                        .nameOfRecipient(req.getNameOfRecipient().trim())
                        .phoneNumber(req.getPhoneNumber().trim())
                        .addressDetail(req.getAddressDetail().trim())
                        .wardCode(req.getWardCode().trim())
                        .wardName(req.getWardName().trim())
                        .districtId(req.getDistrictId().trim())
                        .districtName(req.getDistrictName().trim())
                        .provinceId(req.getProvinceId().trim())
                        .provinceName(req.getProvinceName().trim())
                        .isDefault(Boolean.FALSE)
                        .active(Boolean.TRUE)
                        .createdBy(currentUser.getId())
                        .createdDate(new Date())
                        .build();
                repository.addressRepository.save(builder);
                return new ResData<>(builder.getId(), PuddyCode.CREATED);
            }
        } catch (Exception e) {
            log.error("----- Address create error: {}", e.getMessage());
            throw new PuddyException(PuddyCode.INTERNAL_SERVER);
        }
    }

    @Override
    public Object updateAddress(CurrentUser currentUser, AddressReq req) {
        log.info("----- Address update start ------");
        AuthValidator.checkCustomer(currentUser);

        AddressEntity addressEntity = repository.addressRepository.findById(req.getId())
                .orElseThrow(() -> new PuddyException(PuddyCode.ADDRESS_NOT_FOUND));

        try {
            addressEntity.setAddressDetail(req.getAddressDetail().trim());
            addressEntity.setProvinceId(req.getProvinceId().trim());
            addressEntity.setProvinceName(req.getProvinceName().trim());
            addressEntity.setDistrictId(req.getDistrictId().trim());
            addressEntity.setDistrictName(req.getDistrictName().trim());
            addressEntity.setWardCode(req.getWardCode().trim());
            addressEntity.setWardName(req.getWardName().trim());
            addressEntity.setNameOfRecipient(req.getNameOfRecipient().trim());
            addressEntity.setPhoneNumber(req.getPhoneNumber().trim());
            addressEntity.setUpdatedBy(currentUser.getId());
            addressEntity.setUpdatedDate(new Date());

            repository.addressRepository.save(addressEntity);

            return new ResData<>(addressEntity.getId(), PuddyCode.OK);
        } catch (Exception e) {
            log.error("----- Address update error: {}", e.getMessage());
            throw new PuddyException(PuddyCode.INTERNAL_SERVER);
        }

    }

    @Override
    public void deActiveAddress(CurrentUser currentUser, String id) {
        log.info("----- Address delete start ------");
        AuthValidator.checkCustomer(currentUser);
        AddressEntity address = repository.addressRepository.findById(id).orElseThrow(() -> new PuddyException(PuddyCode.ADDRESS_NOT_FOUND));
        address.setActive(false);
        repository.addressRepository.save(address);
    }

    @Override
    public void setAddressDefault(CurrentUser currentUser, String id) {
        log.info("----- Address set default start ------");
        AuthValidator.checkCustomer(currentUser);
        List<AddressEntity> listAddress = repository.addressRepository.getAddressByUserId(currentUser.getId());
        listAddress.forEach(address -> {
            address.setIsDefault(Boolean.FALSE);

            if (address.getId().equals(id)) {
                address.setIsDefault(Boolean.TRUE);
            }
        });
        repository.addressRepository.saveAll(listAddress);


    }

    @Override
    public Object getAddressById(CurrentUser currentUser, String id) {
        log.info("----- Address get ById start ------");
        AuthValidator.checkCustomer(currentUser);

        AddressEntity address = repository.addressRepository.findById(id)
                .orElseThrow( () -> new PuddyException(PuddyCode.ADDRESS_NOT_FOUND));

        String s5 = ", ";
        String combination = String.join(s5, address.getAddressDetail(), address.getWardName(), address.getDistrictName(), address.getProvinceName());

        AddressRes res = AddressRes.builder()
                .id(address.getId())
                .nameOfRecipient(address.getNameOfRecipient())
                .addressDetail(address.getAddressDetail())
                .phoneNumber(address.getPhoneNumber())
                .provinceId(address.getProvinceId())
                .provinceName(address.getProvinceName())
                .districtId(address.getDistrictId())
                .districtName(address.getDistrictName())
                .wardCode(address.getWardCode())
                .wardName(address.getWardName())
                .isDefault(address.getIsDefault())
                .active(address.getActive())
                .userId(address.getUserId())
                .combination(combination)
                .build();
        return new ResData<>(res, PuddyCode.OK);
    }

    @Override
    public Object getAddressDefault(CurrentUser currentUser) {
        log.info("----- Address get default start ------");
        AuthValidator.checkCustomer(currentUser);
        AddressEntity address = repository.addressRepository
                .getAddressDefaultByUserId(currentUser.getId());
        log.info("address default : {}" , JsonUtils.toJson(address));
        AddressRes res = AddressRes.builder()
                .id(address.getId())
                .nameOfRecipient(address.getNameOfRecipient())
                .addressDetail(address.getAddressDetail())
                .phoneNumber(address.getPhoneNumber())
                .provinceId(address.getProvinceId())
                .provinceName(address.getProvinceName())
                .districtId(address.getDistrictId())
                .districtName(address.getDistrictName())
                .wardCode(address.getWardCode())
                .wardName(address.getWardName())
                .isDefault(address.getIsDefault())
                .active(address.getActive())
                .userId(address.getUserId())
                .combination(null)
                .build();
        return new ResData<>(res, PuddyCode.OK);
    }
}
