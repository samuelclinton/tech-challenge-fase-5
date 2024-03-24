package com.ecommerce.storage.api.controller;

import com.ecommerce.storage.api.model.ChangePriceDto;
import com.ecommerce.storage.api.model.NewItemDto;
import com.ecommerce.storage.domain.model.Item;
import com.ecommerce.storage.domain.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemControllerImpl implements ItemController {

    private final ItemService itemService;

    public ItemControllerImpl(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    @GetMapping("/{id}")
    public Item get(@PathVariable String id) {
        return itemService.get(id);
    }

    @Override
    @GetMapping
    public Page<Item> list(@RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "asc") String sortDirection,
                           @RequestParam(defaultValue = "name") String sortProperty) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortProperty);
        return itemService.list(PageRequest.of(page, size, sort));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item register(@RequestBody @Valid NewItemDto newItemDto) {
        Item newItem = new Item(null, newItemDto.getName(), newItemDto.getPrice());
        return itemService.register(newItem);
    }

    @Override
    @PutMapping("/{itemId}/price")
    public Item changePrice(@PathVariable String itemId, @RequestBody @Valid ChangePriceDto changePriceDto) {
        return itemService.changePrice(itemId, changePriceDto.getNewPrice());
    }

    @Override
    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String itemId) {
        itemService.delete(itemId);
    }

}
