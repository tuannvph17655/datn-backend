package com.datn.utils.base.rest;

import com.datn.utils.constants.PuddyCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ResData<T> {
    private T data;
    private Integer statusCode;
    private String message;
    private Date timeStamp;

    public ResData(T data, PuddyCode puddyCode) {
        this.data = data;
        this.statusCode = Integer.parseInt(puddyCode.getCode());
        this.message = puddyCode.getMessage();
        this.timeStamp = new Date();
    }

    public ResData(T data, PuddyCode puddyCode, String message) {
        this.data = data;
        this.statusCode = Integer.parseInt(puddyCode.getCode());
        this.message = message;
        this.timeStamp = new Date();
    }

    public ResData(boolean isEmpty) {
        this.data = null;
        this.statusCode = HttpStatus.NO_CONTENT.value();
        this.message = HttpStatus.NO_CONTENT.getReasonPhrase();
        this.timeStamp = new Date();
    }

    public static <T> ResData ok(T data) {
        return new ResData(data, PuddyCode.OK);
    }

    public static <T> ResData ok(T data, String message) {
        return new ResData(data, PuddyCode.OK, message);
    }
}
