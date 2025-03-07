package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.dto.StatisticsResponseDTO;
import dev.valente.desafiovagaitau.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estatistica")
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping
    @Operation(
            summary = "Mostrar estatísticas",
            responses = {
                    @ApiResponse(description = "Solicita transferência",
                            responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StatisticsResponseDTO.class))),
            }
    )
    public ResponseEntity<StatisticsResponseDTO> showStatistics() {

        var statistics = statisticsService.getStatistics();
        var statisticsResponseDTO = StatisticsResponseDTO.builder()
                .sum(statistics.getSum())
                .max(statistics.getMax())
                .min(statistics.getMin())
                .average(statistics.getAverage())
                .count(statistics.getCount())
                .build();

        return ResponseEntity.ok(statisticsResponseDTO);
    }
}
