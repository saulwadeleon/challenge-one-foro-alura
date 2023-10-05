package com.alura.foroalura.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * La clase SpringDocConfigurations se utiliza para personalizar la
 * documentación de la API generada por Springdoc OpenAPI al agregar un esquema
 * de seguridad para la autenticación basada en tokens JWT.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    // @Bean
    public void message() {
        System.out.println("bearer is working");
    }
}
