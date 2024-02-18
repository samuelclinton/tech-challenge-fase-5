package com.ecommerce.auth.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public class NewUserDto {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;

    @NotBlank
    @CPF
    private final String cpf;

    @NotBlank
    private final String authority;

    public NewUserDto(String email, String password, String cpf, String authority) {
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.authority = authority;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCpf() {
        return cpf;
    }

    public String getAuthority() {
        return authority;
    }

}
