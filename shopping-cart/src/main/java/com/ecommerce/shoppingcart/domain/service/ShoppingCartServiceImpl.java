package com.ecommerce.shoppingcart.domain.service;

import com.ecommerce.shoppingcart.domain.model.CartItem;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;
import com.ecommerce.shoppingcart.domain.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public ShoppingCart getOrCreateNew(String costumerId) {
        Optional<ShoppingCart> existingShoppingCart = shoppingCartRepository.findByCostumerId(costumerId);
        if (existingShoppingCart.isPresent()) {
            return existingShoppingCart.get();
        } else {
            ShoppingCart newSHoppingCart = new ShoppingCart(null, costumerId, BigDecimal.ZERO);
            return shoppingCartRepository.save(newSHoppingCart);
        }
    }

    @Override
    @Transactional
    public ShoppingCart addItem(String costumerId, CartItem item) {
        ShoppingCart shoppingCart = getOrCreateNew(costumerId);
        shoppingCart.addItem(item);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart removeItem(String costumerId, CartItem item) {
        ShoppingCart shoppingCart = getOrCreateNew(costumerId);
        shoppingCart.removeItem(item);
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart clear(String costumerId) {
        ShoppingCart shoppingCart = getOrCreateNew(costumerId);
        shoppingCart.clear();
        return shoppingCartRepository.save(shoppingCart);
    }

}
