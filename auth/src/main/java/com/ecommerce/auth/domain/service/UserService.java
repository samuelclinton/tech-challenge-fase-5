package com.ecommerce.auth.domain.service;

import com.ecommerce.auth.domain.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(User newUser);
    User authenticate(String email, String password);
    User changeEmail(String userId, String newEmail, String password);
    void changePassword(String userId, String newPassword, String password);
    User changeAuthority(String userId, String newAuthority);
    void delete(String userId);

}
