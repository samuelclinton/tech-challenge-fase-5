package com.ecommerce.auth.domain.exception;

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

}
