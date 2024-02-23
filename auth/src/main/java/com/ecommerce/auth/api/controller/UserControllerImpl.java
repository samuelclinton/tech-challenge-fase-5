package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.ChangeAuthorityDto;
import com.ecommerce.auth.api.model.ChangeEmailDto;
import com.ecommerce.auth.api.model.ChangePasswordDto;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PutMapping("/{userId}/email")
    public Mono<User> changeEmail(@PathVariable String userId, @RequestBody @Valid ChangeEmailDto changeEmailDto) {
        return userService.changeEmail(userId, changeEmailDto.getNewEmail(), changeEmailDto.getPassword());
    }

    @Override
    @PutMapping("/{userId}/password")
    public Mono<Void> changePassword(@PathVariable String userId,
                                     @RequestBody @Valid ChangePasswordDto changePasswordDto) {
        return userService.changePassword(userId, changePasswordDto.getNewPassword(), changePasswordDto.getPassword());
    }

    @Override
    @PutMapping(value = "/{userId}/authority", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> changeAuthority(@PathVariable String userId,
                                      @RequestBody @Valid ChangeAuthorityDto changeAuthorityDto) {
        return userService.changeAuthority(userId, changeAuthorityDto.getNewAuthority());
    }

    @Override
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> delete(@PathVariable String userId) {
        return userService.delete(userId);
    }

}
