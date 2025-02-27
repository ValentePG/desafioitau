package dev.valente.desafiovagaitau.dto;

import lombok.Builder;

@Builder
public record StatisticsResponseDTO(double sum, double average, double min, double max, long count) {
}
