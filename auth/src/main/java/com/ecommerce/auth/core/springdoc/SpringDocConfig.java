package com.ecommerce.auth.core.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        var tags = new ArrayList<Tag>();
        tags.add(new Tag().name("Authentication").description("Gerencia cadastro e autenticação de usuários"));
        tags.add(new Tag().name("User").description("Gerencia os usuários"));
        return new OpenAPI()
                .info(new Info()
                        .title("Auth API")
                        .version("v1")
                        .description("API de autenticação do e-commerce"))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório")
                        .url("https://github.com/samuelclinton/tech-challenge-fase-5"))
                .tags(tags);
    }

}
