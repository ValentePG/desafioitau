package dev.valente.desafiovagaitau.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Statistics {

    private double sum;
    private double average;
    @Setter
    private double min;
    @Setter
    private double max;
    private long count;
}
