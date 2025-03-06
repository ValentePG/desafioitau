package dev.valente.desafiovagaitau.utils;

import dev.valente.desafiovagaitau.domain.Transaction;
import dev.valente.desafiovagaitau.dto.StatisticsResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class StatisticsUtil {

    public static Queue<Transaction> QUEUE = new ConcurrentLinkedQueue<>();
    public static final Queue<Transaction> EMPTY_QUEUE = new ConcurrentLinkedQueue<>();
    public static final StatisticsResponseDTO STATISTICS_FOR_TIMEWINDOW60 =
            StatisticsResponseDTO.builder()
                    .sum(656.0)
                    .average(new BigDecimal("328.0").stripTrailingZeros().doubleValue())
                    .min(121.0)
                    .max(535.0)
                    .count(2L)
                    .build();

    public static final StatisticsResponseDTO STATISTICS_FOR_TIMEWINDOW90 =
            StatisticsResponseDTO.builder()
                    .sum(1263.0)
                    .average(new BigDecimal("315.75").stripTrailingZeros().doubleValue())
                    .min(121.0)
                    .max(535.0)
                    .count(4L)
                    .build();

    public static final StatisticsResponseDTO STATISTICS_FOR_TIMEWINDOW120 =
            StatisticsResponseDTO.builder()
                    .sum(1618.0)
                    .average(new BigDecimal("323.6").stripTrailingZeros().doubleValue())
                    .min(121.0)
                    .max(535.0)
                    .count(5L)
                    .build();

    public static final Transaction TRANSACTION = Transaction.builder()
            .valor(BigDecimal.valueOf(120.00))
            .dataHora(OffsetDateTime.now())
            .build();

    public void initiateQueue() {
        var offsetDateTime = OffsetDateTime.now();
        var offsetDateTimeMinus60 = offsetDateTime.minusSeconds(60);
        var offsetDateTimeMinus90 = offsetDateTime.minusSeconds(90);
        var offsetDateTimeMinus120 = offsetDateTime.minusSeconds(120);

        var transaction1 = Transaction.builder()
                .valor(BigDecimal.valueOf(535.00))
                .dataHora(offsetDateTimeMinus60)
                .build();
        var transaction2 = Transaction.builder()
                .valor(BigDecimal.valueOf(121.00))
                .dataHora(offsetDateTimeMinus60)
                .build();
        var transaction3 = Transaction.builder()
                .valor(BigDecimal.valueOf(356.00))
                .dataHora(offsetDateTimeMinus90)
                .build();
        var transaction4 = Transaction.builder()
                .valor(BigDecimal.valueOf(251.00))
                .dataHora(offsetDateTimeMinus90)
                .build();
        var transaction5 = Transaction.builder()
                .valor(BigDecimal.valueOf(355.00))
                .dataHora(offsetDateTimeMinus120)
                .build();


        QUEUE.add(transaction1);
        QUEUE.add(transaction2);
        QUEUE.add(transaction3);
        QUEUE.add(transaction4);
        QUEUE.add(transaction5);
    }

    public void clearQueue() {
        QUEUE.clear();
    }
}
