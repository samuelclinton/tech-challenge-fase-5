package com.ecommerce.shoppingcart.api.controller;

import com.ecommerce.shoppingcart.domain.model.Item;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;
import reactor.core.publisher.Mono;

public interface ShoppingCartController {

    Mono<ShoppingCart> get(String costumerId);
    Mono<ShoppingCart> create(String costumerId);
    Mono<ShoppingCart> addItem(String costumerId, Item item);
    Mono<ShoppingCart> removeItem(String costumerId, Item item);
    Mono<Void> clear(String costumerId);

}
