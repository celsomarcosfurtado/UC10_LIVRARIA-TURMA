package br.senac.sp.livraria.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("API de Gerenciamento de Livros")
                                .version("1.1.5")
                                .description("Documentação da API REST para CRUD de livros e editoras.")
                                .contact(
                                        new Contact()
                                                .name("Equipe de Backend")
                                                .email("backend@livraria.com.br")
                                )
                );
    }

}
