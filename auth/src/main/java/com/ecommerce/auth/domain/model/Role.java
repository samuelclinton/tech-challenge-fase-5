package com.ecommerce.auth.domain.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    private final String name;

    @Override
    public String getAuthority() {
        return this.name;
    }

    public Role(String name) {
        this.name = name;
    }

}
