package com.datn.utils.common;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumUtils {

    public static <T extends Enum<T>> Set<String> getEnumNames(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants()).map(Enum::toString).collect(Collectors.toSet());
    }
}
