package com.ecommerce.auth.api.model;

import jakarta.validation.constraints.NotBlank;

public class ChangePasswordDto {

    @NotBlank
    private final String newPassword;

    @NotBlank
    private final String password;

    public ChangePasswordDto(String newPassword, String password) {
        this.newPassword = newPassword;
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getPassword() {
        return password;
    }

}
