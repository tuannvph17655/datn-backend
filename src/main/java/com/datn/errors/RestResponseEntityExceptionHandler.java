package com.datn.errors;

import com.datn.message.ResponseMessage;
import com.datn.message.ResponseObject;
import com.datn.utils.common.ApiCollectionUtils;
import com.datn.utils.common.ApiStringUtils;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Log4j2
@RestControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({BindException.class})
    public ResponseEntity<ResponseObject> handleBindException(BindException exception, ContentCachingRequestWrapper request) {
        Map<String, String> errorMap = exception.getBindingResult()
                .getFieldErrors().stream()
                .collect(
                        Collectors.toMap(FieldError::getField,
                                error -> Optional.ofNullable(error.getDefaultMessage()).orElseGet(error::getCode),
                                (origin, duplicated) -> origin)
                );
        return ResponseObject.build(HttpStatus.BAD_REQUEST, "", ResponseMessage.FAILED.getMessage(), errorMap);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ResponseObject> handleConstraintViolationException(ConstraintViolationException exception, ContentCachingRequestWrapper request) {
        Map<String, String> errorMap = ApiCollectionUtils
                .mapOf(
                        exception.getConstraintViolations(),
                        x -> {
                            String path = ApiStringUtils.afterOf(".", x.getPropertyPath().toString());
                            return path.isEmpty() ? x.getPropertyPath().toString() : path;
                        },
                        ConstraintViolation::getMessage
                );
        return ResponseObject.build(HttpStatus.BAD_REQUEST, "", ResponseMessage.FAILED.getMessage(), errorMap);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ResponseObject> handleServiceException(ServiceException exception, ContentCachingRequestWrapper request) {
        exception.printStackTrace();
        return ResponseObject.build(exception.getStatus(), exception.getCode(), exception.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleAllException(Exception exception, ContentCachingRequestWrapper request) {
        if (exception instanceof ServiceException) {
            return handleServiceException((ServiceException) exception, request);
        }
        Throwable cause = ExceptionUtils.getRootCause(exception);
        if (cause instanceof ServiceException) {
            return handleServiceException((ServiceException) cause, request);
        }

        exception.printStackTrace();
        String message = Optional
                .ofNullable(ExceptionUtils.getRootCause(exception))
                .map(Throwable::getMessage)
                .orElse(ExceptionUtils.getRootCauseMessage(exception));

        return ResponseObject.failed(HttpStatus.INTERNAL_SERVER_ERROR.toString(), message);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseObject> handelMaxUploadSizeExceededException(MaxUploadSizeExceededException exception, ContentCachingRequestWrapper request) {
        exception.printStackTrace();
        return ResponseObject.build(HttpStatus.BAD_REQUEST, String.format("Dung lượng file không được quá (%s)MB", exception.getMaxUploadSize()));
    }


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity<String> handleControllerException(AccessDeniedException ex) {
        return new ResponseEntity<>(ex.getMessage(), UNAUTHORIZED);
    }
}