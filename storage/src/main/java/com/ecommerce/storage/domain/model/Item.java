package com.ecommerce.storage.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class Item {

    private String id;
    private String name;
    private BigDecimal price;

}
