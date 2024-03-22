package com.ecommerce.shoppingcart.domain.repository;

import com.ecommerce.shoppingcart.domain.model.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

    Optional<ShoppingCart> findByCostumerId(String costumerId);

}
