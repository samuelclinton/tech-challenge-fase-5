package com.ecommerce.shoppingcart.domain.service;

import com.ecommerce.shoppingcart.api.model.NewCartItemDto;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart getOrCreateNew(String costumerId);
    ShoppingCart addItem(String costumerId, NewCartItemDto newCartItemDto);
    ShoppingCart removeItem(String costumerId, String itemId);
    ShoppingCart clear(String costumerId);

}
