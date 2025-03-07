package dev.valente.desafiovagaitau.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record TransactionDTO(@NotNull(message = "O valor não pode estar vazio")
                             @PositiveOrZero(message = "O Valor da transação deve ser positivo ou igual a 0")
                             @Schema(description = "Valor da transação",
                                     example = "230.45")
                             BigDecimal valor,
                             @NotNull(message = "O campo dataHora não deve estar vazio")
                             @Schema(description = "Data e hora da transação",
                                     example = "2025-03-07T19:26:12.729-03:00")
                             OffsetDateTime dataHora) {
}
