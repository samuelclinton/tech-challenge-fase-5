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
    private Set<CartItem> cartItems = new HashSet<>();
    private BigDecimal subtotal = BigDecimal.ZERO;

    public void updateSubtotal() {
        if (this.cartItems.isEmpty()) {
            this.subtotal = BigDecimal.ZERO;
        } else {
            this.subtotal = BigDecimal.ZERO;
            for (CartItem cartItem : this.cartItems) {
                this.subtotal = this.subtotal.add(cartItem.calculateTotalPrice());
            }
        }
    }

    public void addItem(CartItem item) {
        this.cartItems.add(item);
        updateSubtotal();
    }

    public void removeItem(CartItem item) {
        this.cartItems.remove(item);
        updateSubtotal();
    }

    public void clear() {
        this.cartItems.clear();
        this.subtotal = BigDecimal.ZERO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(String costumerId) {
        this.costumerId = costumerId;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

}
