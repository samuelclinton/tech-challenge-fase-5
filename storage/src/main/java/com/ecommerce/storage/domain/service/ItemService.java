package com.ecommerce.storage.domain.service;

import com.ecommerce.storage.domain.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ItemService {

    Page<Item> list(Pageable pageable);
    Item register(Item item);
    Item changePrice(String itemId, BigDecimal newPrice);
    void delete(String itemId);

}
