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

@RestController
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PutMapping("/{userId}/email")
    public User changeEmail(@PathVariable String userId, @RequestBody @Valid ChangeEmailDto changeEmailDto) {
        return userService.changeEmail(userId, changeEmailDto.getNewEmail(), changeEmailDto.getPassword());
    }

    @Override
    @PutMapping("/{userId}/password")
    public void changePassword(@PathVariable String userId,
                                     @RequestBody @Valid ChangePasswordDto changePasswordDto) {
        userService.changePassword(userId, changePasswordDto.getNewPassword(), changePasswordDto.getPassword());
    }

    @Override
    @PutMapping(value = "/{userId}/authority", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User changeAuthority(@PathVariable String userId,
                                      @RequestBody @Valid ChangeAuthorityDto changeAuthorityDto) {
        return userService.changeAuthority(userId, changeAuthorityDto.getNewAuthority());
    }

    @Override
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String userId) {
        userService.delete(userId);
    }

}
