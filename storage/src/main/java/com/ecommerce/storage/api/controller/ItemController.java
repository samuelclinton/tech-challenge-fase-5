package com.ecommerce.storage.api.controller;

import com.ecommerce.storage.api.model.ChangePriceDto;
import com.ecommerce.storage.api.model.NewItemDto;
import com.ecommerce.storage.domain.model.Item;
import org.springframework.data.domain.Page;

public interface ItemController {

    Page<Item> list(int size, int page, String sortDirection, String sortProperty);

    Item register(NewItemDto newItemDto);
    Item changePrice(String itemId, ChangePriceDto changePriceDto);
    void delete(String itemId);

}
