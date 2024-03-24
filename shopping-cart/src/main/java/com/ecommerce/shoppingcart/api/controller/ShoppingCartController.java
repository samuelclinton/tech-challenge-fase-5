package com.ecommerce.shoppingcart.api.controller;

import com.ecommerce.shoppingcart.api.model.NewCartItemDto;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;

public interface ShoppingCartController {

    ShoppingCart get(String costumerId);
    ShoppingCart addItem(String costumerId, NewCartItemDto newCartItemDto);
    ShoppingCart removeItem(String costumerId, String itemId);
    ShoppingCart clear(String costumerId);

}
