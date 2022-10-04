package com.datn.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceException extends RuntimeException {
    private String code;
    private HttpStatus status;
    private Object[] args;

    public ServiceException(HttpStatus status, String msg, String statusCode) {
        super(msg);
        this.status = status;
        this.code = statusCode;
    }

    public ServiceException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public ServiceException(HttpStatus status, String msg, String statusCode, Object[] args) {
        super(msg);
        this.status = status;
        this.code = statusCode;
        this.args = args;
    }


}
