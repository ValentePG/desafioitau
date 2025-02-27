package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.dto.TransactionDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transacao")
public class TransactionController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> salvarTransacao(@RequestBody TransactionDTO transaction) {

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacoes() {
        return ResponseEntity.ok().build();
    }
}
