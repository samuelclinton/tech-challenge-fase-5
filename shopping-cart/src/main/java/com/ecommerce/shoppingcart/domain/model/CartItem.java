package com.ecommerce.shoppingcart.domain.model;

import com.ecommerce.shoppingcart.api.model.Item;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer amount;

    public CartItem() {
    }

    public CartItem(String id) {
        this.id = id;
    }

    public CartItem(Integer amount, Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.amount = amount;
    }

    public void addAmount(Integer amount) {
        this.amount += amount;
    }

    public void removeAmount(Integer amount) {
        this.amount -= amount;
    }

    public BigDecimal calculateTotalPrice() {
        return this.price.multiply(BigDecimal.valueOf(this.amount));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;

        return Objects.equals(id, cartItem.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
