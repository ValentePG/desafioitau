package dev.valente.desafiovagaitau.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record TransactionDTO(@NotNull @PositiveOrZero BigDecimal valor, @NotNull OffsetDateTime dataHora) {
}
