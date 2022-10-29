package com.datn.utils.common;

import org.springframework.beans.BeanUtils;

public class ApiUtils {
    public static <T> T copy(final Object source, T target, String... ignores){
        if (source == null) return null;
        BeanUtils.copyProperties(source, target, ignores);
        return target;
    }

    public static <T> T copy(final Object source, T target){
        if (source == null) return null;
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
