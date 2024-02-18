package com.ecommerce.storage.domain.service;

import com.ecommerce.storage.domain.model.Item;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface ItemService {

    Flux<Item> list(Pageable pageable);
    Mono<Item> register(Item item);
    Mono<Item> changePrice(String itemId, BigDecimal newPrice);
    Mono<Void> delete(String itemId);

}
