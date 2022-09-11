package com.desafio.sessoesdevoto.infraestrutura.configuracoes;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracoesSwagger {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API de Votação para Associados")
                        .description("API para registro de Pautas, abertura de sessões de votação e registro de votos")
                        .version("v1"))

                .externalDocs(new ExternalDocumentation()
                        .description("Documentação do projeto no GitHub")
                        .url("https://github.com/mateusberger/sessoes-de-voto"));
    }
}
