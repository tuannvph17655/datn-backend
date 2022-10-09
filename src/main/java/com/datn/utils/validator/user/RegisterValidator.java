package com.datn.utils.validator.user;

import com.datn.dto.customer.user.RegisterDto;
import com.datn.utils.base.PuddyException;
import com.datn.utils.base.PuddyRepository;
import com.datn.utils.common.StringUtils;
import com.datn.utils.common.ValidatorUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyConst;
import com.datn.utils.constants.field.RegisterField;
import com.datn.utils.validator.ValidateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

/**
 * @author myname
 */
public class RegisterValidator {

    private RegisterValidator() {}

    public static void validateRegisterDto(RegisterDto body) {
        if (StringUtils.isNullOrEmpty(body.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.NOT_BLANK, PuddyConst.Nouns.EMAIL.toLowerCase(Locale.ROOT)));
        } else if (!ValidateUtils.isValidEmail(body.getEmail().trim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.INVALID, PuddyConst.Nouns.EMAIL));
        }
        if (StringUtils.isNullOrEmpty(body.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.NOT_BLANK, PuddyConst.Nouns.PASSWORD_VI.toLowerCase(Locale.ROOT)));
        } else if (!ValidateUtils.isValidPassword(body.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.INVALID, PuddyConst.Nouns.PASSWORD_VI));
        }
        if (StringUtils.isNullOrEmpty(body.getFirstName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.NOT_BLANK, PuddyConst.Nouns.FIRST_NAME_VI.toLowerCase(Locale.ROOT)));
        } else if (!ValidateUtils.isValidFullName(body.getFirstName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.INVALID, PuddyConst.Nouns.FIRST_NAME_VI));
        }
        if (StringUtils.isNullOrEmpty(body.getLastName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.NOT_BLANK, PuddyConst.Nouns.LAST_NAME_VI.toLowerCase(Locale.ROOT)));
        } else if (!ValidateUtils.isValidFullName(body.getFirstName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.INVALID, PuddyConst.Nouns.LAST_NAME_VI));
        }
        if (body.getDob() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.NOT_BLANK, PuddyConst.Nouns.DOB_VI.toLowerCase(Locale.ROOT)));
        } else if (!ValidateUtils.isValidCustomerAge(body.getDob())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.INVALID_CUSTOMER_AGE, PuddyConst.Values.CUSTOMER_AGE_MIN, PuddyConst.Values.CUSTOMER_AGE_MAX));
        }
        if (body.getGender() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(PuddyConst.Messages.NOT_BLANK, PuddyConst.Nouns.GENDER_VI.toLowerCase(Locale.ROOT)));
        }
    }

    public static void validDtoData(com.datn.dto.customer.user.register.RegisterDto dto) {
        ValidatorUtils.validNullOrEmpty(RegisterField.FIRST_NAME, dto.getFirstName());
        ValidatorUtils.validOnlyCharacter(RegisterField.FIRST_NAME, dto.getFirstName());
        ValidatorUtils.validLength(RegisterField.FIRST_NAME, dto.getFirstName(), 1, 100);

        ValidatorUtils.validNullOrEmpty(RegisterField.LAST_NAME, dto.getLastName());
        ValidatorUtils.validOnlyCharacter(RegisterField.LAST_NAME, dto.getLastName());
        ValidatorUtils.validLength(RegisterField.LAST_NAME, dto.getLastName(), 1, 100);

        ValidatorUtils.validNullOrEmpty(RegisterField.EMAIL, dto.getEmail());
        ValidatorUtils.validEmail(RegisterField.EMAIL, dto.getEmail());
        ValidatorUtils.validLength(RegisterField.EMAIL, dto.getEmail(), 5, 100);

        ValidatorUtils.validNullOrEmpty(RegisterField.PHONE, dto.getPhone());
        ValidatorUtils.validPhone(RegisterField.PHONE, dto.getPhone());

        ValidatorUtils.validNullOrEmpty(RegisterField.DOB, dto.getDob());
        ValidatorUtils.validDateFormat(RegisterField.DOB, dto.getDob());
        ValidatorUtils.validNotMoreNow(RegisterField.DOB, dto.getDob());
        ValidatorUtils.validAgeBetween(RegisterField.DOB, dto.getDob(), 14, 125);

        ValidatorUtils.validNullOrEmpty(RegisterField.PASSWORD, dto.getPassword());
        ValidatorUtils.validOnlyCharacterAndNumber(RegisterField.PASSWORD, dto.getPassword());
        ValidatorUtils.validLength(RegisterField.PASSWORD, dto.getPassword(), 4, 50);
    }

    public static void validDtoConstrains(com.datn.dto.customer.user.register.RegisterDto dto, PuddyRepository repository) {
        if (repository.userRepository.existsByEmailIgnoreCaseAndActive(dto.getEmail().trim(), true)) {
            throw new PuddyException(PuddyCode.EMAIL_EXISTS);
        }
        if (repository.userRepository.existsByPhoneAndActive(dto.getPhone().trim(), true)) {
            throw new PuddyException(PuddyCode.PHONE_EXISTS_V2);
        }
    }
}
