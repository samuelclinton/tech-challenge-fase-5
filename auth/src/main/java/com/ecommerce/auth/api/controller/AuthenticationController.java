package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.LoginDto;
import com.ecommerce.auth.api.model.NewUserDto;
import com.ecommerce.auth.domain.model.User;
import reactor.core.publisher.Mono;

public interface AuthenticationController {

    Mono<User> register(NewUserDto newUserDto);
    Mono<User> authenticate(LoginDto loginDto);

}
