package com.ecommerce.auth.domain.service;

import com.ecommerce.auth.core.properties.JwtProperties;
import com.ecommerce.auth.core.security.AuthenticatedUser;
import com.ecommerce.auth.domain.exception.InvalidTokenException;
import com.ecommerce.auth.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService implements TokenService {

    private final JwtProperties jwtProperties;
    private final UserService userService;

    private SecretKey jwtSecretKey;

    public JwtService(JwtProperties jwtProperties, UserService userService) {
        this.jwtProperties = jwtProperties;
        this.userService = userService;
    }

    @PostConstruct
    public void buildKey() {
        this.jwtSecretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String createToken(String email, String authority) {
        var now = new Date();
        var expiration = new Date(now.getTime() + jwtProperties.getValidity());
        return Jwts
                .builder()
                .claim("authority", authority)
                .subject(email)
                .issuedAt(now)
                .expiration(expiration)
                .signWith(this.jwtSecretKey)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().verifyWith(this.jwtSecretKey).build().parseSignedClaims(token).getPayload();
            Date agora = new Date();
            return !claims.getExpiration().before(agora);
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public String parseAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null) {
            throw new InvalidTokenException("Token de autenticação não configurado corretamente na requisição");
        } else {
            return authorizationHeader.substring(7);
        }
    }

    @Override
    public AuthenticatedUser generateAuthenticatedUserData(String token) {
        Claims claims = Jwts.parser().verifyWith(this.jwtSecretKey).build().parseSignedClaims(token).getPayload();
        return new AuthenticatedUser(claims.getSubject(), claims.get("authority", String.class));
    }

    @Override
    public Authentication generateAuthentication(String token) {
        Claims claims = Jwts.parser().verifyWith(this.jwtSecretKey).build().parseSignedClaims(token).getPayload();
        User user = (User) userService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(user, user.getUsername(), user.getAuthorities());
    }

}
