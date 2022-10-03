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

    public ResponseObject(HttpStatus status, String code, String message, Object data) {
        this.status = status.value();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseObject(HttpStatus status, ResponseMessage responseMessage) {
        this.status = status.value();
        this.code = responseMessage.getCode();
        this.message = responseMessage.getMessage();
    }

    public ResponseObject(HttpStatus status, String code, String message) {
        this.status = status.value();
        this.code = code;
        this.message = message;
    }

    public static ResponseEntity<ResponseObject> build(HttpStatus status, String code, String message, Object data) {
        return ResponseEntity.status(status).body(new ResponseObject(status, code, message, data));
    }

    public static ResponseEntity<ResponseObject> failed(String code, String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR, code, message));
    }

}
