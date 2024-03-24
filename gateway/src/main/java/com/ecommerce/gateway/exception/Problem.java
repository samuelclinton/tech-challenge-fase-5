package com.ecommerce.gateway.exception;

import java.time.Instant;

public class Problem {

    private Integer code;
    private String status;
    private String message;
    private final Instant date = Instant.now();

    public Problem(Integer code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public Problem() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getDate() {
        return date;
    }
}
