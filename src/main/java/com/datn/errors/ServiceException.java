package com.datn.errors;

import com.datn.message.ResponseMessage;
import com.datn.utils.common.ApiStringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {

    private String code;
    private HttpStatus status;
    private Object[] args;

    public ServiceException(HttpStatus status, String msg, String statusCode) {
        super(msg);
        this.status = status;
        this.code = statusCode;
    }

    public ServiceException(HttpStatus status, String msg, String statusCode, Object[] args) {
        super(msg);
        this.status = status;
        this.code = statusCode;
        this.args = args;
    }

    public ServiceException(HttpStatus status, ResponseMessage responseMessage) {
        super(responseMessage.getMessage());
        this.status = status;
        this.code = responseMessage.getCode();

    }

    @Override
    public String getMessage() {
        if (args != null) {
            return ApiStringUtils.format(super.getMessage(), args);
        }
        return super.getMessage();
    }

    public ServiceException(HttpStatus httpStatus, String message) {
        super(message);
        this.status = httpStatus;
        this.code = httpStatus.name();
    }

    public ServiceException(HttpStatus httpStatus, String message, Object... args) {
        super(ApiStringUtils.format(message, args));
        this.status = httpStatus;
        this.code = httpStatus.name();
    }

    public static ServiceException badRequest(String message, Object... args) {
        return new ServiceException(HttpStatus.BAD_REQUEST, message, args);
    }
    
    public static ServiceException notFound(String message, Object... args) {
        return new ServiceException(HttpStatus.NOT_FOUND, message, args);
    }
    
    public static ServiceException failed(String message, Object... args) {
        return new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, message, args);
    }

    public static ServiceException forbidden(String message, Object... args) {
        return new ServiceException(HttpStatus.FORBIDDEN, message, args);
    }

}