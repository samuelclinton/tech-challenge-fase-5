package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.LoginDto;
import com.ecommerce.auth.api.model.NewUserDto;
import com.ecommerce.auth.domain.model.Authority;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/authentication")
public class AuthenticationControllerImpl implements AuthenticationController {

    private final UserService userService;

    public AuthenticationControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> register(@RequestBody @Valid NewUserDto newUserDto) {
        User newUser = new User(null,
                newUserDto.getEmail(),
                newUserDto.getPassword(),
                newUserDto.getCpf(),
                Authority.valueOf(newUserDto.getAuthority()));
        return userService.register(newUser);
    }

    @Override
    @PostMapping("/login")
    public Mono<User> authenticate(@RequestBody @Valid LoginDto loginDto) {
        return userService.authenticate(loginDto.getEmail(), loginDto.getPassword());
    }

}
