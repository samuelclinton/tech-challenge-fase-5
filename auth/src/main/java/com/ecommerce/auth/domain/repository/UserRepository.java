package com.ecommerce.auth.domain.repository;

import com.ecommerce.auth.domain.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByEmail(String email);
    Mono<User> findByCpf(String cpf);

}
