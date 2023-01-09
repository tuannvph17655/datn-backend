package com.datn.message;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseObject {

    private Integer status;

    private String code;
    private String message;
    private Object data;

    public ResponseObject(HttpStatus status, ResponseMessage responseMessage, Object data) {
        this.status = status.value();
        this.code = responseMessage.getCode();
        this.message = responseMessage.getMessage();
        this.data = data;
    }

    public ResponseObject(HttpStatus status, String code, String message, Object data) {
        this.status = status.value();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseObject(HttpStatus status, String code, String message) {
        this.status = status.value();
        this.code = code;
        this.message = message;
    }

    public ResponseObject(HttpStatus status, ResponseMessage responseMessage) {
        this.status = status.value();
        this.code = responseMessage.getCode();
        this.message = responseMessage.getMessage();
    }

    public static ResponseEntity<ResponseObject> success() {
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, ResponseMessage.SUCCESS));
    }

    public static ResponseEntity<ResponseObject> success(Object data) {
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, ResponseMessage.SUCCESS, data));
    }

    public static ResponseEntity<ResponseObject> success(String code, String message) {
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, code, message));
    }

    public static ResponseEntity<ResponseObject> success(Object data, String code, String message) {
        return ResponseEntity.ok().body(new ResponseObject(HttpStatus.OK, code, message, data));
    }

    public static ResponseEntity<ResponseObject> createSuccess(Object data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject(HttpStatus.CREATED, ResponseMessage.SUCCESS, data));
    }

    public static ResponseEntity<ResponseObject> badRequest() {
        return ResponseEntity.badRequest().body(new ResponseObject(HttpStatus.BAD_REQUEST, ResponseMessage.FAILED));
    }

    public static ResponseEntity<Object> badRequest(String code, String message) {
        return ResponseEntity.badRequest().body(new ResponseObject(HttpStatus.BAD_REQUEST, code, message));
    }


    public static ResponseEntity<Object> badRequest(ResponseMessage responseMessage) {
        return ResponseEntity.badRequest().body(new ResponseObject(HttpStatus.BAD_REQUEST, responseMessage));
    }

    public static ResponseEntity<ResponseObject> notFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND, ResponseMessage.NOT_FOUND));
    }

    public static ResponseEntity<ResponseObject> notFound(ResponseMessage responseMessage) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject(HttpStatus.NOT_FOUND, responseMessage));
    }

    public static ResponseEntity<ResponseObject> failed(String code, String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR, code, message));
    }

    public static ResponseEntity<ResponseObject> build(HttpStatus status, String code, String message, Object data) {
        return ResponseEntity.status(status).body(new ResponseObject(status, code, message, data));
    }

    public static ResponseEntity<ResponseObject> build(HttpStatus status, ResponseMessage responseMessage, Object data) {
        return ResponseEntity.status(status).body(new ResponseObject(status, responseMessage, data));
    }

    public static ResponseEntity<ResponseObject> build(final HttpStatus status, final String message) {
        return ResponseEntity.status(status).body(new ResponseObject(status, status.name(), message, null));
    }

}
