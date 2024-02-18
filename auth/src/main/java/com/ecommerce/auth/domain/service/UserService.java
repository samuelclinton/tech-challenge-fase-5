package com.ecommerce.auth.domain.service;

import com.ecommerce.auth.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> register(User newUser);
    Mono<User> authenticate(String email, String password);
    Mono<User> changeEmail(String userId, String newEmail, String password);
    Mono<Void> changePassword(String userId, String newPassword, String password);
    Mono<User> changeAuthority(String userId, String newAuthority);
    Mono<Void> delete(String userId);

}
