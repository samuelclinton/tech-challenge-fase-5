package com.ecommerce.auth.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String email;
    private String password;
    private final String cpf;
    private Authority authority;
    private String cartId;

    public User(String id, String email, String password, String cpf, Authority authority) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.authority = authority;
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public boolean passwordIsIncorrect(PasswordEncoder passwordEncoder, String password) {
        return !passwordEncoder.matches(password, this.password);
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

}
