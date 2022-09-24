package com.datn.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    SUCCESS("", ""),
    DATETIME_FORMAT("", ""),
    INTERNAL_SERVER_ERROR("", ""),
    SOMETHING_WRONG("", ""),
    NOT_FOUND("", ""),
    FAILED("Lỗi",""),

    DATA_CANNOT_BE_NULL( "DATA_CANNOT_BE_NULL", "Data error %s can't not be null");

    private final String code;
    private final String message;
}

