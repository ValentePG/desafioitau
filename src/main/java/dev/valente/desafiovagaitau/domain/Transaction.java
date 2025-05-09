package dev.valente.desafiovagaitau.domain;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Transaction {

    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
