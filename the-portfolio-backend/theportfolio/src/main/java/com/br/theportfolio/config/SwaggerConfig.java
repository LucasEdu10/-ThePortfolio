package com.br.theportfolio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {

        /* ----- 1. Metadados ----- */
        Info info = new Info()
                .title("API Portfólio")
                .description("Backend do Portfolio")
                .version("v1.0");

        /* ----- 2. Esquema Bearer JWT ----- */
        SecurityScheme bearerScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization")
                .description("Insira **Bearer &lt;token&gt;**");

        /* ----- 3. Exige o esquema por padrão ----- */
        SecurityRequirement requirement = new SecurityRequirement().addList("bearer-key");

        /* ----- 4. Monta o objeto OpenAPI ----- */
        return new OpenAPI()
                .info(info)
                .components(new Components().addSecuritySchemes("bearer-key", bearerScheme))
                .addSecurityItem(requirement);
    }
}

