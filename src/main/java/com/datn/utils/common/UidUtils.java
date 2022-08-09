package com.datn.utils.common;

import java.util.Locale;
import java.util.Random;
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
        var uid = generateUid();
        return uid.substring(uid.lastIndexOf("-") + 1).substring(4).toUpperCase(Locale.ROOT);
    }

    public static String generateToken(Integer length) {
        if (length == null || length < 1) length = 6;
        var min = Integer.valueOf("1" + "0".repeat(length - 1));
        var max = Integer.valueOf("9" + "9".repeat(length - 1));
        var randomValue = new Random().nextInt(max - min) + min;
        return randomValue + "";
    }
}
