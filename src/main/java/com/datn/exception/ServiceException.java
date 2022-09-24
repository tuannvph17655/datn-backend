package com.datn.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceException extends RuntimeException {
    private String code;
    private HttpStatus status;
    private Object[] args;

}
