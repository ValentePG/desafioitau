package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.dto.TransactionDTO;
import dev.valente.desafiovagaitau.singleton.SingletonHashMap;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transacao")
@Log4j2
@RequiredArgsConstructor
public class TransactionController {

    private final SingletonHashMap singletonHashMap = SingletonHashMap.getInstance();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> salvarTransacao(@RequestBody @Valid TransactionDTO transaction) {

        singletonHashMap.getMap().get(1L).accept(transaction.valor().doubleValue());

        log.info("average:'{}'", singletonHashMap.getMap().get(1L).getAverage());
        log.info("max:'{}'", singletonHashMap.getMap().get(1L).getMax());
        log.info("min:'{}'", singletonHashMap.getMap().get(1L).getMin());
        log.info("count:'{}'", singletonHashMap.getMap().get(1L).getCount());
        log.info("sum:'{}'", singletonHashMap.getMap().get(1L).getSum());


        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacoes() {
        return ResponseEntity.ok().build();
    }
}
