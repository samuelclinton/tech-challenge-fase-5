package com.ecommerce.storage.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    SEM_PROBLEMAS("Sem problemas", "sem-problemas"),
    RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "recurso-nao-encontrado"),
    ENTIDADE_EM_USO("Entidade em uso", "entidade-em-uso"),
    MENSAGEM_INCOMPREENSIVEL("Mensagem incrompreensível", "mensagem-incompreensivel"),
    PARAMETRO_INVALIDO("Parâmetro inválido", "parametro-invalido"),
    ERRO_DE_SISTEMA("Erro de sistema", "erro-de-sistema"),
    DADOS_INVALIDOS("Dados inválidos", "dados-invalidos"),
    ACESSO_NEGADO("Acesso negado", "acesso-negado"),
    ERRO_NEGOCIO("Violação de regra de negócio", "erro-negocio");

    private final String title;
    private final String uri;

    ProblemType(String title, String path) {
        this.title = title;
        this.uri = "https://ecommerce.com.br/" + path;
    }

}
