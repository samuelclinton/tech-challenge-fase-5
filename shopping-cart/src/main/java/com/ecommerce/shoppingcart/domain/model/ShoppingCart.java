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
    private final Set<Item> items = new HashSet<>();
    private BigDecimal subtotal;

    public ShoppingCart(String id, String costumerId, BigDecimal subtotal) {
        this.id = id;
        this.costumerId = costumerId;
        this.subtotal = subtotal;
    }

    public void addItem(Item item) {
        this.items.add(item);
        this.subtotal = this.subtotal.add(item.getTotalPrice());
    }

    public void removeItem(Item item) {
        this.items.remove(item);
        this.subtotal = this.subtotal.subtract(item.getTotalPrice());
    }

    public void clear() {
        this.items.forEach(this::removeItem);
    }

}
