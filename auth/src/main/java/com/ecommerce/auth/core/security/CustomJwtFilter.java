package com.ecommerce.auth.core.security;

import com.ecommerce.auth.domain.exception.InvalidTokenException;
import com.ecommerce.auth.domain.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final List<String> unsecuredPaths = List.of("/login", "/register", "/validate-token");

    public CustomJwtFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        String token = tokenService.parseAuthorizationHeader(request.getHeader("Authorization"));
        if (token != null && tokenService.validateToken(token)) {
            Authentication authentication = tokenService.generateAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            throw new InvalidTokenException("Token de acesso inválido ou expirado");
        }
    }

    @Override
    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) {
        boolean shouldNotFilter = false;
        for (String unsecuredPath : this.unsecuredPaths) {
            var matcher = new AntPathRequestMatcher(unsecuredPath);
            if (matcher.matches(request)) {
                shouldNotFilter = true;
            }
        }
        return shouldNotFilter;
    }

}
