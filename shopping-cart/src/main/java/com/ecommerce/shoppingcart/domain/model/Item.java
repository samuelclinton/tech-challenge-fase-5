package com.ecommerce.shoppingcart.domain.model;

import java.math.BigDecimal;

public class Item {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer amount;

    public BigDecimal getTotalPrice() {
        return this.price.multiply(BigDecimal.valueOf(this.amount));
    }

}
