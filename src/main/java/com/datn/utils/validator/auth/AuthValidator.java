package com.datn.utils.validator.auth;

import com.datn.utils.base.PuddyException;
import com.datn.utils.base.rest.CurrentUser;
import com.datn.utils.common.JsonUtils;
import com.datn.utils.constants.PuddyCode;
import com.datn.utils.constants.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Luôn dùng class này trong các api phân quyền
 */
@Slf4j
public class AuthValidator {
    private AuthValidator() {
    }

    public static void checkAdmin(CurrentUser currentUser) {
        log.info("AuthValidator checkAdmin: {}", JsonUtils.toJson(currentUser));
        if (!currentUser.getRole().equals(RoleEnum.ROLE_ADMIN)) {
            throw new PuddyException(PuddyCode.FORBIDDEN);
        }
    }

    public static void checkStaff(CurrentUser currentUser) {
        checkLogin(currentUser);
        log.info("AuthValidator checkStaff: {}", JsonUtils.toJson(currentUser));
        if (!currentUser.getRole().equals(RoleEnum.ROLE_STAFF)) {
            throw new PuddyException(PuddyCode.FORBIDDEN);
        }
    }

    public static void checkCustomer(CurrentUser currentUser) {
        checkLogin(currentUser);
        log.info("AuthValidator checkCustomer: {}", JsonUtils.toJson(currentUser));
        if (!RoleEnum.ROLE_CUSTOMER.equals(currentUser.getRole())) {
            throw new PuddyException(PuddyCode.FORBIDDEN);
        }
    }

    public static void checkRole(CurrentUser currentUser, RoleEnum roleAvailable) {
        checkLogin(currentUser);
        log.info("AuthValidator checkRole: {}, roleAvailable: {}", JsonUtils.toJson(currentUser), JsonUtils.toJson(roleAvailable));
        if (!currentUser.getRole().equals(roleAvailable)) {
            throw new PuddyException(PuddyCode.FORBIDDEN);
        }
    }

    public static void checkRole(CurrentUser currentUser, RoleEnum... roleAvailableList) {
        checkLogin(currentUser);
        log.info("AuthValidator checkRole: {}, roleAvailableList: {}", JsonUtils.toJson(currentUser), JsonUtils.toJson(roleAvailableList));
        if (!Arrays.stream(roleAvailableList).anyMatch(role -> role.equals(currentUser.getRole()))) {
            throw new PuddyException(PuddyCode.FORBIDDEN);
        }

    }

    public static void checkLogin(CurrentUser currentUser) {
        log.info("AuthValidator checkLogin: {}", JsonUtils.toJson(currentUser));
        if (currentUser == null) {
            throw new PuddyException(PuddyCode.FORBIDDEN);
        }
    }
}
