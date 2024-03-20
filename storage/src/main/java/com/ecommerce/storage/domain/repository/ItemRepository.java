package com.ecommerce.storage.domain.repository;

import com.ecommerce.storage.domain.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    Optional<Item> findByName(String name);

}
