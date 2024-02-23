package com.ecommerce.auth.core.security;

import com.ecommerce.auth.domain.model.Authority;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class RootUserSeed {

    private final UserRepository userRepository;

    public RootUserSeed(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        User existingUser = userRepository.findByEmail("root@ecommerce.com").block();
        if (existingUser == null) {
            User root = new User(
                    null,
                    "root@ecommerce.com",
                    "secret",
                    "66744682075",
                    Authority.ADMIN);
            userRepository.save(root).block();
        }
    }

}
