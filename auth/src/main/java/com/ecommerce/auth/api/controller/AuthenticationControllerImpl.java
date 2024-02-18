package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.LoginDto;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/authentication")
public class AuthenticationControllerImpl implements AuthenticationController {

    private final UserService userService;

    public AuthenticationControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping("/login")
    public Mono<User> authenticate(@RequestBody @Valid LoginDto loginDto) {
        return userService.authenticate(loginDto.getEmail(), loginDto.getPassword());
    }

}
