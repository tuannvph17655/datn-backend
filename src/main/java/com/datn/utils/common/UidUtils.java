package com.datn.utils.common;

import java.util.Locale;
import java.util.UUID;

public class UidUtils {

    private UidUtils() {
    }

    public static String generateUid() {
        return UUID.randomUUID().toString();
    }

    public static String cleanUid(String uid) {
        return uid.replace("\"", "").trim();
    }

    public static String generateVoucher() {
        String uid = generateUid();
        return uid.substring(uid.lastIndexOf("-") + 1).substring(4).toUpperCase(Locale.ROOT);
    }

}
