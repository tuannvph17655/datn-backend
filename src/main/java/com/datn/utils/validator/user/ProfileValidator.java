package com.datn.utils.validator.user;

import com.datn.dto.customer.user.ProfileDto;
import com.datn.entity.UserEntity;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.common.BeanUtils;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.common.StringUtils;
import com.datn.utils.common.ValidatorUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.field.UserFields;
import com.datn.utils.validator.admin.user.AdminUserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
public class ProfileValidator {

    private ProfileValidator() {
    }

    public static void validDto(ProfileDto dto) {
        log.info("start validDto with dto: {}", JsonUtils.toJson(dto));
        PuddyRepository repository = BeanUtils.getBean(PuddyRepository.class);
        ValidatorUtils.validNullOrEmpty(UserFields.FIRST_NAME, dto.getFirstName());
        ValidatorUtils.validLength(UserFields.FIRST_NAME, dto.getFirstName(), 100, true);
        ValidatorUtils.validOnlyCharacter(UserFields.FIRST_NAME, dto.getFirstName());
        ValidatorUtils.validNullOrEmpty(UserFields.LAST_NAME, dto.getLastName());
        ValidatorUtils.validLength(UserFields.LAST_NAME, dto.getLastName(), 100, true);
        ValidatorUtils.validOnlyCharacter(UserFields.LAST_NAME, dto.getLastName());
        ValidatorUtils.validNullOrEmpty(UserFields.PHONE, dto.getPhone());
        ValidatorUtils.validPhone(UserFields.PHONE, dto.getPhone());
        ValidatorUtils.validBooleanType(UserFields.GENDER, dto.getGender());
        ValidatorUtils.validAgeBetween(UserFields.DOB, dto.getDob(), 14, 115);
        if (!StringUtils.isNullOrEmpty(dto.getPassword()) || !StringUtils.isNullOrEmpty(dto.getNewPassword())) {
            ValidatorUtils.validNullOrEmpty(UserFields.PASSWORD, dto.getPassword());
            ValidatorUtils.validLength(UserFields.PASSWORD, dto.getPassword(), 6, 100);
            validOldPassword(repository, dto);
            ValidatorUtils.validNullOrEmpty(UserFields.NEW_PASSWORD, dto.getNewPassword());
            ValidatorUtils.validLength(UserFields.NEW_PASSWORD, dto.getNewPassword(), 6, 100);
            ValidatorUtils.validNewPassNotSameOldPass(dto.getPassword(), dto.getNewPassword());
        }
        AdminUserValidator.validPhoneMustBeUnique(repository, dto.getPhone(), dto.getId());
        AdminUserValidator.validEmailMustBeUnique(repository, dto.getEmail(), dto.getId());
    }

    private static void validOldPassword(PuddyRepository repository, ProfileDto dto) {
        UserEntity user = repository.userRepository.findByIdAndActive(dto.getId(), true);
        if (null == user) {
            throw new PuddyException(PuddyCode.INTERNAL_SERVER);
        }
        PasswordEncoder passwordEncoder = BeanUtils.getBean(PasswordEncoder.class);
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new PuddyException(PuddyCode.PASSWORD_WRONG);
        }
    }
}
