package com.ecommerce.auth.core.security;

public record AuthenticatedUser(String email, String authority) {
}
