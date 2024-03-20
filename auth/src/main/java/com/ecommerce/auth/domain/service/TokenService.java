package com.ecommerce.auth.domain.service;

import com.ecommerce.auth.core.security.AuthenticatedUser;
import org.springframework.security.core.Authentication;

public interface TokenService {

    String createToken(String email, String role);
    boolean validateToken(String token);

    String parseAuthorizationHeader(String authorizationHeader);
    AuthenticatedUser generateAuthenticatedUserData(String token);
    Authentication generateAuthentication(String token);

}
