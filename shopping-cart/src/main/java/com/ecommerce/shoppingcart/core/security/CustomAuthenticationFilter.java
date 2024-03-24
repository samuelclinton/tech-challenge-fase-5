package com.ecommerce.shoppingcart.core.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final List<String> unsecuredPaths = List.of("/v3/api-docs/**", "/docs.html", "/swagger-ui/**");

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String user = request.getHeader("user");
        String authority = request.getHeader("authority");
        if (user != null || authority != null) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    user, null, Set.of(new SimpleGrantedAuthority(authority)));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            throw new AccessDeniedException("Dados de usuário autenticado não fornecidos");
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
