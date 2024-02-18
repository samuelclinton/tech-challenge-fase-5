package com.ecommerce.auth.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ChangeEmailDto {

    @NotBlank
    @Email
    private final String newEmail;

    @NotBlank
    private final String password;

    public ChangeEmailDto(String newEmail, String password) {
        this.newEmail = newEmail;
        this.password = password;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public String getPassword() {
        return password;
    }

}
