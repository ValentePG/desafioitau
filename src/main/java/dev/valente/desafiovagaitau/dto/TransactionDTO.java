package dev.valente.desafiovagaitau.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record TransactionDTO(@NotNull(message = "O valor não pode estar vazio")
                             @PositiveOrZero(message = "O Valor da transação deve ser positivo ou igual a 0")
                             BigDecimal valor,
                             @NotNull(message = "O campo dataHora não deve estar vazio")
                             OffsetDateTime dataHora) {
}
