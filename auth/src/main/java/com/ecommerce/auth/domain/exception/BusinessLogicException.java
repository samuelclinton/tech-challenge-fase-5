package com.ecommerce.auth.domain.exception;

public abstract class BusinessLogicException extends RuntimeException {

    protected BusinessLogicException(String message) {
        super(message);
    }

}
