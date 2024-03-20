package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.LoginDto;
import com.ecommerce.auth.api.model.NewUserDto;
import com.ecommerce.auth.api.model.TokenDto;
import com.ecommerce.auth.domain.model.User;
import org.springframework.http.ResponseEntity;

public interface AuthenticationController {

    User register(NewUserDto newUserDto);
    TokenDto login(LoginDto loginDto);

    ResponseEntity<Void> validateToken(String authorizationHeader);

}
