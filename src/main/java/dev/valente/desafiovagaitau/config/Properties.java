package dev.valente.desafiovagaitau.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "properties")
public record Properties(int timeWindow) {
}
