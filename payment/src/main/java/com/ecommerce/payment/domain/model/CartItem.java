package com.ecommerce.payment.domain.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItem {

    private String id;
    private String name;
    private BigDecimal price;
    private Integer amount;

}
