package com.ecommerce.auth.core.security;

import com.ecommerce.auth.domain.model.Role;
import com.ecommerce.auth.domain.model.RoleType;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RootUserSeed {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RootUserSeed(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (userRepository.findByEmail("root@ecommerce.com").isEmpty()) {
            User root = new User(
                    null,
                    "root@ecommerce.com",
                    "secret",
                    "66744682075",
                    new Role(RoleType.ADMIN.name()));
            root.encryptPassword(this.passwordEncoder);
            userRepository.save(root);
        }
    }

}
