package dev.valente.desafiovagaitau.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record StatisticsResponseDTO(double sum, double average, double min, double max, long count) {
}
