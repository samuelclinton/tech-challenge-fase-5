package com.ecommerce.auth.domain.exception;

public class UserAuthenticationException extends BusinessLogicException {

    public UserAuthenticationException() {
        super("Erro na autenticação do usuário, verifique as credenciais de acesso");
    }

}
