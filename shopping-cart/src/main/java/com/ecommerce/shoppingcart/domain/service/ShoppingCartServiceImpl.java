package com.ecommerce.shoppingcart.domain.service;

import com.ecommerce.shoppingcart.domain.exception.NoShoppingCartFoundException;
import com.ecommerce.shoppingcart.domain.model.Item;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;
import com.ecommerce.shoppingcart.domain.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public Mono<ShoppingCart> get(String costumerId) {
        return shoppingCartRepository
                .findByCostumerId(costumerId)
                .switchIfEmpty(Mono.error(() -> new NoShoppingCartFoundException(costumerId)));
    }

    @Override
    public Mono<ShoppingCart> create(String costumerId) {
        ShoppingCart newSHoppingCart = new ShoppingCart(null, costumerId, BigDecimal.ZERO);
        return shoppingCartRepository.save(newSHoppingCart);
    }

    @Override
    public Mono<ShoppingCart> addItem(String costumerId, Item item) {
        Mono<ShoppingCart> existingShoppingCart = get(costumerId);
        return existingShoppingCart
                .flatMap(shoppingCart -> {
                    shoppingCart.addItem(item);
                    return shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public Mono<ShoppingCart> removeItem(String costumerId, Item item) {
        Mono<ShoppingCart> existingShoppingCart = get(costumerId);
        return existingShoppingCart
                .flatMap(shoppingCart -> {
                    shoppingCart.removeItem(item);
                    return shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public Mono<Void> clear(String costumerId) {
        Mono<ShoppingCart> existingShoppingCart = get(costumerId);
        return existingShoppingCart
                .flatMap(shoppingCart -> {
                    shoppingCart.clear();
                    return shoppingCartRepository.save(shoppingCart)
                            .then(Mono.empty());
                });
    }

}
