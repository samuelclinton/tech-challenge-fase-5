package com.ecommerce.storage.domain.exception;

public class ItemAlreadyExistsException extends RuntimeException {

    public ItemAlreadyExistsException(String name) {
        super("Já existe um item cadastrado com o nome " + name);
    }

}
