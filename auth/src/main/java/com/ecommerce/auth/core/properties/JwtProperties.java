package com.ecommerce.auth.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("ecommerce.security.jwt")
public class JwtProperties {

    /*
     * O segredo usado para gerar a chave de assinatura dos tokens de acesso.
     */
    private String secret;

    /*
     * A validade do token de acesso em mili-segundos.
     * Por padrão 300000 (5 minutos).
     */
    private int validity = 300000;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

}
