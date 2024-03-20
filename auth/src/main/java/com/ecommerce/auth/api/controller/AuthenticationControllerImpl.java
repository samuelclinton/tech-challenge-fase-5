package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.LoginDto;
import com.ecommerce.auth.api.model.NewUserDto;
import com.ecommerce.auth.api.model.TokenDto;
import com.ecommerce.auth.core.security.AuthenticatedUser;
import com.ecommerce.auth.domain.exception.InvalidTokenException;
import com.ecommerce.auth.domain.model.Role;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.service.TokenService;
import com.ecommerce.auth.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationControllerImpl implements AuthenticationController {

    private final UserService userService;
    private final TokenService tokenService;

    public AuthenticationControllerImpl(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Valid NewUserDto newUserDto) {
        User newUser = new User(null,
                newUserDto.getEmail(),
                newUserDto.getPassword(),
                newUserDto.getCpf(),
                new Role(newUserDto.getAuthority()));
        return userService.register(newUser);
    }

    @Override
    @PostMapping("/login")
    public TokenDto login(@RequestBody @Valid LoginDto loginDto) {
        User user = userService.authenticate(loginDto.getEmail(), loginDto.getPassword());
        String token = tokenService.createToken(user.getEmail(), user.getRole().getAuthority());
        return new TokenDto(token);
    }

    @Override
    @GetMapping("/validate-token")
    public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = tokenService.parseAuthorizationHeader(authorizationHeader);
        if (tokenService.validateToken(token)) {
            AuthenticatedUser authenticatedUser = tokenService.generateAuthenticatedUserData(token);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("user", authenticatedUser.email());
            httpHeaders.set("authority", authenticatedUser.authority());
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).build();
        } else {
            throw new InvalidTokenException("Token de autenticação inválido ou expirado");
        }
    }

}
