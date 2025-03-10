package dev.valente.desafiovagaitau.config.openapi;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@Builder
@Getter
public class SchemaBadRequest {
    @Schema(description = "TimeStamp da exceção", example = "2025-03-07T21:54:43.653-03:00")
    private OffsetDateTime timestamp;
    @Schema(description = "Status code da exceção", example = "400")
    private int status;
    @Schema(description = "Descrição do erro", example = "Bad request")
    private String error;
    @Schema(description = "Mensagem do erro", example = "JSON parse error")
    private String message;
    @Schema(description = "Path do erro", example = "/transacao")
    private String path;
}
