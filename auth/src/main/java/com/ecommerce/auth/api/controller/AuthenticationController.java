package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.LoginDto;
import com.ecommerce.auth.api.model.NewUserDto;
import com.ecommerce.auth.api.model.TokenDto;
import com.ecommerce.auth.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication")
public interface AuthenticationController {

    @Operation(
            summary = "Cadastra um usuário",
            description = "Cadastra um usuário"
    )
    User register(NewUserDto newUserDto);

    @Operation(
            summary = "Autentica um usuário",
            description = "Autentica um usuário"
    )
    TokenDto login(LoginDto loginDto);

    @Operation(hidden = true)
    ResponseEntity<Void> validateToken(String authorizationHeader);

}
