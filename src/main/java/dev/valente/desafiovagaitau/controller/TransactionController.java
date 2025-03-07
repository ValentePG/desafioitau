package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.domain.Transaction;
import dev.valente.desafiovagaitau.dto.TransactionDTO;
import dev.valente.desafiovagaitau.service.TransactionService;
import dev.valente.desafiovagaitau.validator.ValidTransactionInThePast;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;

@RestController
@RequestMapping("transacao")
@Log4j2
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final ValidTransactionInThePast validator;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {

        var formattedData = transactionDTO.dataHora()
                .atZoneSameInstant(ZoneId.of("America/Sao_Paulo")).toOffsetDateTime();

        validator.validTransactionInThePast(formattedData);

        var transaction = Transaction.builder()
                .valor(transactionDTO.valor())
                .dataHora(formattedData)
                .build();

        transactionService.saveTransaction(transaction);

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransaction() {

        transactionService.clearAll();

        return ResponseEntity.ok().build();
    }
}
