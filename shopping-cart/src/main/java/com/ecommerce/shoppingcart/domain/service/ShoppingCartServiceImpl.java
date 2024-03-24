package com.ecommerce.shoppingcart.domain.service;

import com.ecommerce.shoppingcart.api.model.Item;
import com.ecommerce.shoppingcart.api.model.NewCartItemDto;
import com.ecommerce.shoppingcart.core.cloud.client.ItemServiceClient;
import com.ecommerce.shoppingcart.domain.model.CartItem;
import com.ecommerce.shoppingcart.domain.model.ShoppingCart;
import com.ecommerce.shoppingcart.domain.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ItemServiceClient itemServiceClient;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   ItemServiceClient itemServiceClient) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.itemServiceClient = itemServiceClient;
    }

    @Override
    public ShoppingCart getOrCreateNew(String costumerId) {
        Optional<ShoppingCart> existingShoppingCart = shoppingCartRepository.findByCostumerId(costumerId);
        if (existingShoppingCart.isPresent()) {
            return existingShoppingCart.get();
        } else {
            ShoppingCart newShoppingCart = new ShoppingCart();
            newShoppingCart.setCostumerId(costumerId);
            return shoppingCartRepository.save(newShoppingCart);
        }
    }

    @Override
    @Transactional
    public ShoppingCart addItem(String costumerId, NewCartItemDto newCartItemDto) {
        ShoppingCart shoppingCart = getOrCreateNew(costumerId);
        Item item = itemServiceClient.getItem(newCartItemDto.getItemId());
        CartItem newCartItem = new CartItem(newCartItemDto.getAmount(), item);

        if (shoppingCart.getCartItems().contains(newCartItem)) {
            for (CartItem cartItem : shoppingCart.getCartItems()) {
                if (cartItem.equals(newCartItem)) {
                    cartItem.setPrice(newCartItem.getPrice());
                    cartItem.addAmount(newCartItemDto.getAmount());
                    shoppingCart.updateSubtotal();
                }
            }
        } else {
            shoppingCart.addItem(newCartItem);
        }
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCart removeItem(String costumerId, String itemId) {
        ShoppingCart shoppingCart = getOrCreateNew(costumerId);
        shoppingCart.removeItem(new CartItem(itemId));
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
