package com.ecommerce.shoppingcart.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "shopping-carts")
public class ShoppingCart {

    @MongoId
    private String id;
    private String costumerId;
    private final Set<CartItem> cartItems = new HashSet<>();
    private BigDecimal subtotal;

    public ShoppingCart(String id, String costumerId, BigDecimal subtotal) {
        this.id = id;
        this.costumerId = costumerId;
        this.subtotal = subtotal;
    }

    public void addItem(CartItem item) {
        this.cartItems.add(item);
        this.subtotal = this.subtotal.add(item.getTotalPrice());
    }

    public void removeItem(CartItem item) {
        this.cartItems.remove(item);
        this.subtotal = this.subtotal.subtract(item.getTotalPrice());
    }

    public void clear() {
        this.cartItems.forEach(this::removeItem);
    }

}
