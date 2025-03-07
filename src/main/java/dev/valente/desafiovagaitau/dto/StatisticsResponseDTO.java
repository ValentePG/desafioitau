package dev.valente.desafiovagaitau.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record StatisticsResponseDTO(@Schema(description = "Soma das estatísticas",
                                            example = "250.56")
                                    double sum,
                                    @Schema(description = "Média das estatísticas",
                                            example = "10.456")
                                    double average,
                                    @Schema(description = "Menor valor registrado nas estatísticas",
                                            example = "10.35")
                                    double min,
                                    @Schema(description = "Maior valor registrado nas estatísticas",
                                            example = "100.35")
                                    double max,
                                    @Schema(description = "Quantidade de transações registrado nas estatísticas",
                                            example = "4")
                                    long count) {
}
