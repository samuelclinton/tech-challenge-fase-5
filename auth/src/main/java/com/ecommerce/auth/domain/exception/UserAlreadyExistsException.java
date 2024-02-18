package com.ecommerce.auth.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String data) {
        super(String.format("Já existe um usuário cadastrado com o dado [%s]", data));
    }

}
