package com.ecommerce.payment.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class ShoppingCart {

    private String id;
    private String costumerId;
    private Set<CartItem> cartItems;
    private BigDecimal subtotal;

}
