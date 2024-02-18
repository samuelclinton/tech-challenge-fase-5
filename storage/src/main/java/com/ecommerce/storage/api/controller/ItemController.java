package com.ecommerce.storage.api.controller;

import com.ecommerce.storage.api.model.ChangePriceDto;
import com.ecommerce.storage.api.model.NewItemDto;
import com.ecommerce.storage.domain.model.Item;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ItemController {

    Flux<Item> list(int size, int page, String sortDirection, String sortProperty);

    Mono<Item> register(NewItemDto newItemDto);
    Mono<Item> changePrice(String itemId, ChangePriceDto changePriceDto);
    Mono<Void> delete(String itemId);

}
