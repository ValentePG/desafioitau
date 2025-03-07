package dev.valente.desafiovagaitau.validator;

import dev.valente.desafiovagaitau.exception.TransactionInTheFuture;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.OffsetDateTime;

@Component
public class ValidTransactionInThePast {

    public void validTransactionInThePast(OffsetDateTime horarioRequisicao) {
        OffsetDateTime now = OffsetDateTime.now();
        if (Duration.between(now, horarioRequisicao).isPositive())
            throw new TransactionInTheFuture("Transação não pode ser feita no futuro");
    }

}
