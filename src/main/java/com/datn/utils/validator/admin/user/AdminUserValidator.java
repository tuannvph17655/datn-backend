package com.datn.utils.validator.admin.user;

import com.datn.dto.admin.info.UserDto;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.ValidatorUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.field.UserFields;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminUserValidator {
    private AdminUserValidator() {
    }

    public static void validCreate(UserDto dto) {
        log.info("AdminUserValidator validCreate start");
        validCreateOrUpdate(dto);
        validOnlyCreate(dto);
        PuddyRepository repository = BeanUtils.getBean(PuddyRepository.class);
        validEmailMustBeUnique(repository, dto.getEmail().trim());
        validPhoneMustBeUnique(repository, dto.getPhone().trim());
    }

    public static void validOnlyCreate(UserDto dto) {
        ValidatorUtils.validNullOrEmpty(UserFields.EMAIL, dto.getEmail());
        ValidatorUtils.validLength(UserFields.EMAIL, dto.getEmail(), 6, 250);
        ValidatorUtils.validEmail(UserFields.EMAIL, dto.getEmail());
        ValidatorUtils.validNullOrEmpty(UserFields.PASSWORD, dto.getPassword());
        ValidatorUtils.validLength(UserFields.PASSWORD, dto.getPassword(), 6, 100);
    }

    public static void validPhoneMustBeUnique(PuddyRepository repository, String phone) {
        log.info("AdminUserValidator validPhoneMustBeUnique start");
        if (repository.userRepository.existsByPhone(phone.trim())) {
            throw new PuddyException(PuddyCode.PHONE_EXISTS);
        }
    }

    public static void validEmailMustBeUnique(PuddyRepository repository, String email) {
        log.info("AdminUserValidator validEmailMustBeUnique start");
        if (repository.userRepository.existsByEmailIgnoreCase(email.trim())) {
            throw new PuddyException(PuddyCode.EMAIL_EXISTS);
        }
    }

    /**
     * khi update chỉ được thay đổi 1 số trường. các trường khác phải giữ nguyên
     */
    public static void validUpdate(UserDto dto) {
        log.info("AdminUserValidator validUpdate start");
        PuddyRepository repository = BeanUtils.getBean(PuddyRepository.class);
        validExists(repository, dto.getId());
        validCreateOrUpdate(dto);
        validPhoneMustBeUnique(repository, dto.getPhone(), dto.getId());
    }

    public static void validPhoneMustBeUnique(PuddyRepository repository, String phone, String id) {
        log.info("AdminUserValidator validPhoneMustBeUnique start");
        if (repository.userRepository.existsByPhoneAndIdNot(phone.trim(), id)) {
            throw new PuddyException(PuddyCode.PHONE_EXISTS);
        }
    }

    public static void validCreateOrUpdate(UserDto dto) {
        ValidatorUtils.validNullOrEmpty(UserFields.FIRST_NAME, dto.getFirstName());
        ValidatorUtils.validLength(UserFields.FIRST_NAME, dto.getFirstName(), 100, true);
        ValidatorUtils.validOnlyCharacter(UserFields.FIRST_NAME, dto.getFirstName());
        ValidatorUtils.validNullOrEmpty(UserFields.LAST_NAME, dto.getLastName());
        ValidatorUtils.validLength(UserFields.LAST_NAME, dto.getLastName(), 100, true);
        ValidatorUtils.validOnlyCharacter(UserFields.LAST_NAME, dto.getLastName());
        ValidatorUtils.validNullOrEmpty(UserFields.PHONE, dto.getPhone());
        ValidatorUtils.validPhone(UserFields.PHONE, dto.getPhone());
        ValidatorUtils.validRole(UserFields.ROLE, dto.getRole());
        ValidatorUtils.validBooleanType(UserFields.GENDER, dto.getGender());
        ValidatorUtils.validAgeBetween(UserFields.DOB, dto.getDob(), 14, 115);
    }

    public static void validExists(PuddyRepository repository, String id) {
        if (!repository.userRepository.existsByIdAndActive(id, true)) {
            throw new PuddyException(PuddyCode.USER_NOT_FOUND);
        }
    }

    public static void validEmailMustBeUnique(PuddyRepository repository, String email, String id) {
        log.info("AdminUserValidator validEmailMustBeUnique start");
        if (repository.userRepository.existsByEmailIgnoreCaseAndIdNot(email.trim(), id)) {
            throw new PuddyException(PuddyCode.EMAIL_EXISTS);
        }
    }
}
