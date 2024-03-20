package com.ecommerce.auth.domain.repository;

import com.ecommerce.auth.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByCpf(String cpf);

}
