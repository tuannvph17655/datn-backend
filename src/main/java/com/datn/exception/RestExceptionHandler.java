package com.datn.exception;

import com.datn.message.ResponseMessage;
import com.datn.message.ResponseObject;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler({BindException.class})
    public ResponseEntity<ResponseObject> handleBindException(BindException exception) {
        Map<String, String> errorMap = exception.getBindingResult()
                .getFieldErrors().stream()
                .collect(
                        Collectors.toMap(FieldError::getField,
                                error -> Optional.ofNullable(error.getDefaultMessage()).orElseGet(error::getCode),
                                (origin, duplicated) -> origin)
                );
        return ResponseObject.build(HttpStatus.BAD_REQUEST, "", ResponseMessage.FAILED.getMessage(), errorMap);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseObject> handleServiceException(ServiceException exception, ContentCachingRequestWrapper request) {
        return ResponseObject.build(exception.getStatus(), exception.getCode(), exception.getMessage(), null);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseObject> handleAllException(Exception exception, ContentCachingRequestWrapper request) {
//        if (exception instanceof ServiceException) {
//            return handleServiceException((ServiceException) exception, request);
//        }
//        Throwable cause = ExceptionUtils.getRootCause(exception);
//        if (cause instanceof ServiceException) {
//            return handleServiceException((ServiceException) cause, request);
//        }
//
//        exception.printStackTrace();
//        String message = Optional
//                .ofNullable(ExceptionUtils.getRootCause(exception))
//                .map(Throwable::getMessage)
//                .orElse(ExceptionUtils.getRootCauseMessage(exception));
//
//        return ResponseObject.failed(HttpStatus.INTERNAL_SERVER_ERROR.toString(), message);
//    }
}
