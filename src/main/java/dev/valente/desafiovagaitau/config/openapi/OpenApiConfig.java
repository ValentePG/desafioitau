package dev.valente.desafiovagaitau.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Desafio Itaú", version = "1.0"))
public class OpenApiConfig {
}
