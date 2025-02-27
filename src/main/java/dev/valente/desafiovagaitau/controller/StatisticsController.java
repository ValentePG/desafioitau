package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.dto.StatisticsResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("estatistica")
public class StatisticsController {

    @GetMapping
    public ResponseEntity<StatisticsResponseDTO> mostrarEstatisticas() {
        return null;
    }
}
