package com.datn.utils.base;

import com.datn.utils.constants.PuddyCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PuddyException extends ResponseStatusException {
    public PuddyException(PuddyCode puddyCode) {
        super(HttpStatus.valueOf(Integer.parseInt(puddyCode.getCode())), puddyCode.getMessage());
    }

    public PuddyException(PuddyCode puddyCode, String message) {
        super(HttpStatus.valueOf(Integer.parseInt(puddyCode.getCode())), message);
    }

    public PuddyException(PuddyCode puddyCode, int code) {
        super(HttpStatus.valueOf(code), puddyCode.getMessage());
    }

    public PuddyException(PuddyCode puddyCode, int code, String message) {
        super(HttpStatus.valueOf(code), message);
    }



}
