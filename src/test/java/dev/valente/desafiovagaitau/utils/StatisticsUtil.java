package dev.valente.desafiovagaitau.utils;

import dev.valente.desafiovagaitau.domain.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class StatisticsUtil {

    public static final Queue<Transaction> QUEUE = new ConcurrentLinkedQueue<>();
    public static final Queue<Transaction> EMPTY_QUEUE = new ConcurrentLinkedQueue<>();

    static {
        var transaction1 = Transaction.builder()
                        .valor(BigDecimal.valueOf(50.00))
                        .dataHora(OffsetDateTime.now())
                        .build();
        var transaction2 = Transaction.builder()
                .valor(BigDecimal.valueOf(100.00))
                .dataHora(OffsetDateTime.now())
                .build();
        var transaction3 = Transaction.builder()
                .valor(BigDecimal.valueOf(500.00))
                .dataHora(OffsetDateTime.now().minusSeconds(90))
                .build();
        var transaction4 = Transaction.builder()
                .valor(BigDecimal.valueOf(200.00))
                .dataHora(OffsetDateTime.now().minusSeconds(120))
                .build();
        var transaction5 = Transaction.builder()
                .valor(BigDecimal.valueOf(300.00))
                .dataHora(OffsetDateTime.now().minusSeconds(90))
                .build();

        QUEUE.add(transaction1);
        QUEUE.add(transaction2);
        QUEUE.add(transaction3);
        QUEUE.add(transaction4);
        QUEUE.add(transaction5);
    }
}
