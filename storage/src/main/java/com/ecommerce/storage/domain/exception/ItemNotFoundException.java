package com.ecommerce.storage.domain.exception;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String id) {
        super("Nenhum item encontrado com o id " + id);
    }

}
