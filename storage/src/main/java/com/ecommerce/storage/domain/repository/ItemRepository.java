package com.ecommerce.storage.domain.repository;

import com.ecommerce.storage.domain.model.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ItemRepository extends ReactiveMongoRepository<Item, String> {

    Flux<Item> findByIdNotNull(Pageable pageable);
    Mono<Item> findByName(String name);

}
