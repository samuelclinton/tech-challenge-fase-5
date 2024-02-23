package com.ecommerce.shoppingcart.api.controller;

import com.ecommerce.shoppingcart.domain.model.Item;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;
import com.ecommerce.shoppingcart.domain.service.ShoppingCartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartControllerImpl implements ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartControllerImpl(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    @GetMapping("/{costumerId}")
    public Mono<ShoppingCart> get(@PathVariable String costumerId) {
        return shoppingCartService.get(costumerId);
    }

    @Override
    @PostMapping
    public Mono<ShoppingCart> create(String costumerId) {
        return shoppingCartService.create(costumerId);
    }

    @Override
    @PutMapping("/{costumerId}/items")
    public Mono<ShoppingCart> addItem(@PathVariable String costumerId, @RequestBody @Valid Item item) {
        return shoppingCartService.addItem(costumerId, item);
    }

    @Override
    @DeleteMapping("/{costumerId}/items")
    public Mono<ShoppingCart> removeItem(@PathVariable String costumerId, Item item) {
        return shoppingCartService.removeItem(costumerId, item);
    }

    @Override
    public Mono<Void> clear(String costumerId) {
        return shoppingCartService.clear(costumerId);
    }

}
