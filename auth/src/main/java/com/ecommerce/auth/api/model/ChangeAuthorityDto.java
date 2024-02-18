package com.ecommerce.auth.api.model;

import jakarta.validation.constraints.NotBlank;

public class ChangeAuthorityDto {

    @NotBlank
    private String newAuthority;

    public void setNewAuthority(String newAuthority) {
        this.newAuthority = newAuthority;
    }

    public String getNewAuthority() {
        return newAuthority;
    }

}
