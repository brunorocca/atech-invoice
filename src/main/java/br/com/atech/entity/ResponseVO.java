package br.com.atech.entity;

import org.springframework.http.HttpStatus;

public class ResponseVO {
    private Integer status;
    private String message;

    public ResponseVO(HttpStatus status) {
        this.status = status.value();
        this.message = status.getReasonPhrase();
    }

    public ResponseVO(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
