package com.datn.utils.common;

import com.datn.utils.base.PuddyException;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.PuddyMessage;
import com.datn.utils.constants.enums.RoleEnum;
import com.datn.utils.constants.field.DiscountFields;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author myname
 */
public class ValidatorUtils {
    private ValidatorUtils() {
        super();
    }
//
    private static final String NOT_BLANK = "Không được để trống ";
    private static final String INVALID = " không hợp lệ";
    private static final String MUST_LENGTH_LESS = " phải ít hơn ";
    private static final String MUST_LENGTH_MORE = " phải nhiều hơn ";
    private static final String CHARACTER = " ký tự";
    private static final String MUST_MORE = " phải lớn hơn ";
    private static final String MUST_LESS = " phải nhỏ hơn ";

    public static void validNullOrEmpty(String fieldName, String value) {
        if (StringUtils.isNullOrEmpty(value)) {
            throw new PuddyException(PuddyCode.BAD_REQUEST, NOT_BLANK + fieldName);
        }
    }

    public static void validNullOrEmpty(String fieldName, List<String> values) {
        if (values == null || values.isEmpty()) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, NOT_BLANK + fieldName);
        }
        for (var value : values) {
            validNullOrEmpty(fieldName, value);
        }
    }

    public static void validNullOrEmptyList(String fieldName, List<Object> values) {
        if (values == null || values.isEmpty()) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, NOT_BLANK + fieldName);
        }
    }

    public static void validOnlyCharacterAndNumber(String fieldName, String value) {
        if (!StringUtils.isOnlyCharacterAndNumber(value)) {
            throw new PuddyException(PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static void validOnlyCharacterAndNumber(String fieldName, List<String> values) {
        if (values.isEmpty()) {
            return;
        }
        for (var value : values) {
            if (!StringUtils.isOnlyCharacterAndNumber(value)) {
                fieldName = fieldName.charAt(0) + fieldName.substring(1);
                throw new PuddyException(PuddyCode.BAD_REQUEST, fieldName + INVALID);
            }
        }
    }
//
    /**
     * valid do dai
     * type = true => valid max length
     */
    public static void validLength(String fieldName, String value, int length, boolean type) {
        if (type && value.length() > length) {
            throw new PuddyException
                    (PuddyCode.BAD_REQUEST, fieldName + MUST_LENGTH_LESS + length + CHARACTER);
        }

        if (!type && value.length() < length) {
            throw new PuddyException
                    (PuddyCode.BAD_REQUEST, fieldName + MUST_LENGTH_MORE + length + CHARACTER);
        }
    }

    public static void validLength(String fieldName, String value, int minLength, int maxLength) {
        if (value.length() < minLength) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + MUST_LENGTH_MORE + minLength + CHARACTER);
        }
        if (value.length() > maxLength) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + MUST_LENGTH_LESS + maxLength + CHARACTER);
        }
    }

    public static void validLength(String fieldName, List<String> values, int minLength, int maxLength) {
        if (values == null || values.isEmpty()) {
            return;
        }
        for (var value : values) {
            if (value.length() < minLength) {
                throw new PuddyException
                (PuddyCode.BAD_REQUEST, fieldName + MUST_LENGTH_MORE + minLength + CHARACTER);
            }
            if (value.length() > maxLength) {
                throw new PuddyException
                (PuddyCode.BAD_REQUEST, fieldName + MUST_LENGTH_LESS + maxLength + CHARACTER);
            }
        }
    }

    public static void validOnlyNumber(String fieldName, String value) {
        if (!StringUtils.isOnlyNumber(value)) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static void validPrice(String fieldName, String value) {
        try {
            if (value.startsWith("0") || value.contains(" ")) {
                throw new PuddyException
                (PuddyCode.BAD_REQUEST, fieldName + INVALID);
            }
            var price = Long.valueOf(value);
            if (price < 0) {
                throw new PuddyException
                (PuddyCode.BAD_REQUEST, fieldName + INVALID);
            }
        } catch (NumberFormatException e) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static void validOnlyCharacter(String fieldName, String value) {
        if (!StringUtils.isOnlyCharacter(value)) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static void validEmail(String fieldName, String value) {
        if (!StringUtils.isValidEmail(value)) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static void validPhone(String fieldName, String value) {
        if (!StringUtils.isCheck(value, StringUtils.PHONE_NUMBER_REGEX)) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static void validRole(String fieldName, String value) {
        validNullOrEmpty(fieldName, value);
        var role = RoleEnum.valueOf(value);
        if (role == null) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static void validBooleanType(String fieldName, Boolean value) {
        if (value == null) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static void validAgeBetween(String fieldName, Date value, Integer minAge, Integer maxAge) {
        if (value == null) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, NOT_BLANK + fieldName.toLowerCase());
        }
        var now = LocalDate.now();
        var dob = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (dob.compareTo(now) > 0) {
            throw new PuddyException
            (PuddyCode.DOB_NOT_MORE_NOW);
        }
        if (null != minAge) {
            now = now.minusYears(minAge);
            if (dob.compareTo(now) > 0) {
                throw new PuddyException
                (PuddyCode.AGE_MUST_MORE, PuddyCode.AGE_MUST_MORE.getMessage() + " " + minAge);
            }
        }
        if (null != maxAge) {
            now = now.minusYears(maxAge);
            if (dob.compareTo(now) < 0) {
                throw new PuddyException
                (PuddyCode.AGE_MUST_LESS, PuddyCode.AGE_MUST_LESS.getMessage() + " " + minAge);
            }
        }
    }

    public static void validNewPassNotSameOldPass(String password, String newPassword) {
        if (newPassword.equals(password)) {
            throw new PuddyException
            (PuddyCode.NEW_PASS_NOT_SAME_OLD);
        }
    }

    public static void validLongValueBetween(String fieldName, String value, Long minValue, Long maxValue) {
        Long percent = Long.valueOf(value);
        if (percent < minValue || percent > maxValue) {
            throw new PuddyException
            (PuddyCode.PERCENT_MUST_BETWEEN_0_AND_100);
        }
    }

    public static void validLongValueMustBeMore(String fieldName, String value, Long minValue) {
        Long percent = Long.valueOf(value);
        if (percent < minValue) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + MUST_MORE + minValue);
        }
    }

    public static void validDateFormat(String fieldName, String value) {
        try {
            var sdf = DateTimeFormatter.ofPattern(DateUtils.F_DDMMYYYY);
            LocalDate localDate = LocalDate.parse(value, sdf);
        } catch (Exception e) {
            throw new PuddyException
            (PuddyCode.DATE_FORMAT_INVALID);
        }
    }
//
    public static void validTimeFormat(String fieldName, String value) {

    }

    public static void validDateMoreDateAndTimeMoreTime(String startDate, String startDateValue, String endDate, String endDateValue, String startTime, String startTimeValue, String endTime, String endTimeValue) {
    }

    public static void validNotContainSpace(String fieldName, String value) {
        if (value.contains(" ")) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + " " + PuddyCode.NOT_CONTAIN_SPACE.getMessage().toLowerCase(Locale.ROOT));
        }
    }
//
    public static void validNotMoreNow(String fieldName, String value) {
        if (DateUtils.toDate(value, DateUtils.F_DDMMYYYY).after(new Date())) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + " " + PuddyMessage.NOT_MORE_NOW.toLowerCase());
        }
    }

    public static void validAgeBetween(String fieldName, String value, int minAge, int maxAge) {
        try {
            Date dateValue = new SimpleDateFormat("dd/MM/yyyy").parse(value);
            validAgeBetween(fieldName, dateValue, minAge, maxAge);
        } catch (Exception e) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static Date validDateTimeFormat(String fieldName, String value) {
        try {
            var sdf = new SimpleDateFormat(DateUtils.F_DDMMYYYYHHMM);
            return sdf.parse(value);
        } catch (Exception e) {
            throw new PuddyException
            (PuddyCode.BAD_REQUEST, fieldName + INVALID);
        }
    }

    public static void validEndDateNotMoreStartDate(String startDate, String endDate) {
        var start = validDateTimeFormat(DiscountFields.START_TIME, startDate);
        var end = validDateTimeFormat(DiscountFields.END_TIME, endDate);
        if (start.after(end)) {
            throw new PuddyException
            (PuddyCode.END_TIME_MUST_MORE_START_TIME);
        }
    }
}
