package com.ecommerce.auth.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    @NotNull
    @Schema(description = "O status HTTP do erro", example = "400")
    private Integer status;

    @NotNull
    @Schema(description = "A data e horário do erro", example = "2024-01-08T16:08:11.7791693-03:00")
    private OffsetDateTime timestamp;

    @NotNull
    @Schema(description = "O tipo de erro", example = "https://cloudinn.com.br/dados-invalidos")
    private String type;

    @NotNull
    @Schema(description = "O título do erro", example = "Dados inválidos")
    private String title;

    @NotNull
    @Schema(description = "Mensagem detalhada do erro",
            example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String detail;

    @Schema(description = "Mensagem para o usuário",
            example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String userMessage;

    @Schema(description = "Lista com os objetos de erro")
    private List<Object> objects;

    @Schema(name = "ProblemObject")
    @Getter
    @Builder
    public static class Object {

        @NotNull
        @Schema(description = "Nome do campo com erro", example = "cnpj")
        private String name;

        @NotNull
        @Schema(description = "Mensagem para o usuário", example = "número do registro de contribuinte corporativo brasileiro (CNPJ) inválido")
        private String userMessage;

    }

}
