package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.StatisticsRepository;
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
import java.time.ZoneOffset;

@RestController
@RequestMapping("transacao")
@Log4j2
@RequiredArgsConstructor
public class TransactionController {

    private final Singletons singletons = Singletons.getInstance();
    private final StatisticsRepository statisticsRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> salvarTransacao(@RequestBody @Valid TransactionDTO transaction) {
        statisticsRepository.addTransaction(transaction.valor().doubleValue());

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
                .filter(l -> Duration.between(l.dataHora(),
                        OffsetDateTime.now(ZoneOffset.of("-03:00"))).getSeconds() <= 60)
                .toList();

        filtrado.forEach(t -> log.info("Não sei:'{}'", t));
        log.info("average:'{}'", statisticsRepository.getStatistics().getAverage());
        log.info("max:'{}'", statisticsRepository.getStatistics().getMax());
        log.info("min:'{}'", statisticsRepository.getStatistics().getMin());
        log.info("count:'{}'", statisticsRepository.getStatistics().getCount());
        log.info("sum:'{}'", statisticsRepository.getStatistics().getSum());


        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacoes() {
        return ResponseEntity.ok().build();
    }
}
