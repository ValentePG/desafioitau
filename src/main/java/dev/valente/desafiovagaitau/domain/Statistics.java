package dev.valente.desafiovagaitau.domain;

import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Statistics {

    private double sum;
    private BigDecimal average;
    @Setter
    private double min;
    @Setter
    private double max;
    private long count;
}
