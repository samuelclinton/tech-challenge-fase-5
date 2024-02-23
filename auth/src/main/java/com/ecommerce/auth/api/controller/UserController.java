package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.ChangeAuthorityDto;
import com.ecommerce.auth.api.model.ChangeEmailDto;
import com.ecommerce.auth.api.model.ChangePasswordDto;
import com.ecommerce.auth.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserController {

    Mono<User> changeEmail(String userId, ChangeEmailDto changeEmailDto);
    Mono<Void> changePassword(String userId,ChangePasswordDto changePasswordDto);

    Mono<User> changeAuthority(String userId, ChangeAuthorityDto changeAuthorityDto);

    Mono<Void> delete(String userId);

}
