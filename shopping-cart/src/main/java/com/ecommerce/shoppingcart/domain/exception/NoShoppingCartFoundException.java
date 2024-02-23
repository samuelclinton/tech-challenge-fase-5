package com.ecommerce.shoppingcart.domain.exception;

public class NoShoppingCartFoundException extends RuntimeException {

    public NoShoppingCartFoundException(String costumerId) {
        super("Nenhum carrinho de compras encontrado para o usuário " + costumerId);
    }

}
