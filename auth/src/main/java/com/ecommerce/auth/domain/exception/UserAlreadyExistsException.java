package com.ecommerce.auth.domain.exception;

public class UserAlreadyExistsException extends BusinessLogicException {

    public UserAlreadyExistsException(String data) {
        super(String.format("Já existe um usuário cadastrado com o dado [%s]", data));
    }

}
