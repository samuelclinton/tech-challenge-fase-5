package com.ecommerce.shoppingcart.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationFilter customAuthenticationFilter;

    public SecurityConfig(CustomAuthenticationFilter customAuthenticationFilter) {
        this.customAuthenticationFilter = customAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sessionConfigurer
                        -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/v3/api-docs/**", "/docs.html", "/swagger-ui/**").permitAll()
                                .anyRequest().authenticated());
        return http.build();
    }

}
