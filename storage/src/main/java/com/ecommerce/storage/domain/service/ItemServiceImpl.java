package com.ecommerce.storage.domain.service;

import com.ecommerce.storage.domain.exception.ItemAlreadyExistsException;
import com.ecommerce.storage.domain.exception.ItemNotFoundException;
import com.ecommerce.storage.domain.model.Item;
import com.ecommerce.storage.domain.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item get(String itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    @Override
    public Page<Item> list(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public Item register(Item newItem) {
        if (itemRepository.findByName(newItem.getName()).isPresent()) {
            throw new ItemAlreadyExistsException(newItem.getName());
        } else {
            return itemRepository.save(newItem);
        }
    }

    @Override
    public Item changePrice(String itemId, BigDecimal newPrice) {
        Item existingItem = get(itemId);
        existingItem.setPrice(newPrice);
        return itemRepository.save(existingItem);
    }

    @Override
    public void delete(String itemId) {
        itemRepository.delete(get(itemId));
    }

}
