package com.ecommerce.shoppingcart.domain.repository;

import com.ecommerce.shoppingcart.domain.model.ShoppingCart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ShoppingCartRepository extends ReactiveMongoRepository<ShoppingCart, String> {

    Mono<ShoppingCart> findByCostumerId(String costumerId);

}
