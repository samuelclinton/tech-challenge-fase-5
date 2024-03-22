package com.ecommerce.shoppingcart.api.controller;

import com.ecommerce.shoppingcart.domain.model.CartItem;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;

public interface ShoppingCartController {

    ShoppingCart get(String costumerId);
    ShoppingCart addItem(String costumerId, CartItem item);
    ShoppingCart removeItem(String costumerId, CartItem item);
    ShoppingCart clear(String costumerId);

}
