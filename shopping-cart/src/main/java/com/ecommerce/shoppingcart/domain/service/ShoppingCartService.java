package com.ecommerce.shoppingcart.domain.service;

import com.ecommerce.shoppingcart.domain.model.CartItem;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart getOrCreateNew(String costumerId);
    ShoppingCart addItem(String costumerId, CartItem item);
    ShoppingCart removeItem(String costumerId, CartItem item);
    ShoppingCart clear(String costumerId);

}
