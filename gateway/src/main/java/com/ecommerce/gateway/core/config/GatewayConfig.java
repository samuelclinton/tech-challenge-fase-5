package com.ecommerce.gateway.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GatewayConfig {

    @Bean
    public List<String> unsecuredEndpoints() {
        return List.of("/login", "/register");
    }

}
