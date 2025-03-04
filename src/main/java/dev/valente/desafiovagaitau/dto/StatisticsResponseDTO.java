package dev.valente.desafiovagaitau.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record StatisticsResponseDTO(double sum, BigDecimal average, double min, double max, long count) {
}
