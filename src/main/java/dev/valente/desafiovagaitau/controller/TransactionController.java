package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.dto.TransactionDTO;
import dev.valente.desafiovagaitau.singleton.Singletons;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.OffsetDateTime;

@RestController
@RequestMapping("transacao")
@Log4j2
@RequiredArgsConstructor
public class TransactionController {

    private final Singletons singletons = Singletons.getInstance();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> salvarTransacao(@RequestBody @Valid TransactionDTO transaction) {

        singletons.getMap().get(1L).accept(transaction.valor().doubleValue());

        log.info("Day:'{}'", transaction.dataHora().getDayOfMonth());
        log.info("Month:'{}'", transaction.dataHora().getMonth());
        log.info("Month value:'{}'", transaction.dataHora().getMonthValue());
        log.info("Year:'{}'", transaction.dataHora().getYear());
        log.info("Hour:'{}'", transaction.dataHora().getHour());
        log.info("Minute:'{}'", transaction.dataHora().getMinute());
        log.info("Second:'{}'", transaction.dataHora().getSecond());

        var list = singletons.getQueue();
        list.add(transaction);

        var filtrado = list
                .stream()
                .filter(l -> Duration.between(l.dataHora(), OffsetDateTime.now()).getSeconds() <= 60)
                .toList();

        filtrado.forEach(t -> log.info("Não sei:'{}'", t));
        log.info("average:'{}'", singletons.getMap().get(1L).getAverage());
        log.info("max:'{}'", singletons.getMap().get(1L).getMax());
        log.info("min:'{}'", singletons.getMap().get(1L).getMin());
        log.info("count:'{}'", singletons.getMap().get(1L).getCount());
        log.info("sum:'{}'", singletons.getMap().get(1L).getSum());


        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacoes() {
        return ResponseEntity.ok().build();
    }
}
