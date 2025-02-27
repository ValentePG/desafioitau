package dev.valente.desafiovagaitau.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Statistics {

    private double sum;
    private double average;
    private double min;
    private double max;
    private long count;
}
