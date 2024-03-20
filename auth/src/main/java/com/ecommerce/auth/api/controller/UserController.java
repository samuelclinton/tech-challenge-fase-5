package com.ecommerce.auth.api.controller;

import com.ecommerce.auth.api.model.ChangeAuthorityDto;
import com.ecommerce.auth.api.model.ChangeEmailDto;
import com.ecommerce.auth.api.model.ChangePasswordDto;
import com.ecommerce.auth.domain.model.User;

public interface UserController {

    User changeEmail(String userId, ChangeEmailDto changeEmailDto);
    void changePassword(String userId,ChangePasswordDto changePasswordDto);

    User changeAuthority(String userId, ChangeAuthorityDto changeAuthorityDto);

    void delete(String userId);

}
