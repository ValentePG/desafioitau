package dev.valente.desafiovagaitau.validator;

import dev.valente.desafiovagaitau.exception.TransactionInTheFuture;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.OffsetDateTime;

@Component
@Log4j2
public class ValidTransactionInThePast {

    public void validTransactionInThePast(OffsetDateTime horarioRequisicao) {
        OffsetDateTime now = OffsetDateTime.now();
        if (Duration.between(now, horarioRequisicao).isPositive()){
            log.warn("Tentativa de salvar transação com data no futuro: {}", horarioRequisicao);
            throw new TransactionInTheFuture("Transação não pode ser feita no futuro");
        }
    }

}
