package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.dto.StatisticsResponseDTO;
import dev.valente.desafiovagaitau.service.StatisticsService;
import lombok.RequiredArgsConstructor;
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
