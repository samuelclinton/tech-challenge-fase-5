package com.ecommerce.shoppingcart.api.controller;

import com.ecommerce.shoppingcart.api.model.NewCartItemDto;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;
import com.ecommerce.shoppingcart.domain.service.ShoppingCartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShoppingCartControllerImpl implements ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartControllerImpl(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    @GetMapping("/{costumerId}")
    public ShoppingCart get(@PathVariable String costumerId) {
        return shoppingCartService.getOrCreateNew(costumerId);
    }

    @Override
    @PutMapping("/{costumerId}/items")
    public ShoppingCart addItem(@PathVariable String costumerId, @RequestBody @Valid NewCartItemDto item) {
        return shoppingCartService.addItem(costumerId, item);
    }

    @Override
    @DeleteMapping("/{costumerId}/items/{itemId}")
    public ShoppingCart removeItem(@PathVariable String costumerId, @PathVariable String itemId) {
        return shoppingCartService.removeItem(costumerId, itemId);
    }

    @Override
    @DeleteMapping("/{costumerId}/clear")
    public ShoppingCart clear(@PathVariable String costumerId) {
        return shoppingCartService.clear(costumerId);
    }

}
