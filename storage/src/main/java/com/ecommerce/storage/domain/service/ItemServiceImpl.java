package com.ecommerce.storage.domain.service;

import com.ecommerce.storage.domain.exception.ItemAlreadyExistsException;
import com.ecommerce.storage.domain.model.Item;
import com.ecommerce.storage.domain.repository.ItemRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Flux<Item> list(Pageable pageable) {
        return itemRepository.findByIdNotNull(pageable);
    }

    @Override
    public Mono<Item> register(Item newItem) {
        Mono<Boolean> existingItem = itemRepository.findByName(newItem.getName()).hasElement();
        return existingItem
                .flatMap(itemAlreadyExistsWithName -> {
                    if (Boolean.TRUE.equals(itemAlreadyExistsWithName)) {
                        return Mono.error(() -> new ItemAlreadyExistsException(newItem.getName()));
                    } else {
                        return itemRepository.save(newItem);
                    }
                });
    }

    @Override
    public Mono<Item> changePrice(String itemId, BigDecimal newPrice) {
        Mono<Item> existingItem = itemRepository.findById(itemId);
        return existingItem
                .flatMap(item -> {
                    item.setPrice(newPrice);
                    return itemRepository.save(item);
                });
    }

    @Override
    public Mono<Void> delete(String itemId) {
        return itemRepository.findById(itemId)
                .flatMap(itemRepository::delete);
    }

}
