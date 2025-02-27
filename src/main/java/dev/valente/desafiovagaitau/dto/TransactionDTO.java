package dev.valente.desafiovagaitau.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record TransactionDTO(BigDecimal valor, OffsetDateTime dataHora) {
}
