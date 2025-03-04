package dev.valente.desafiovagaitau.service;

import dev.valente.desafiovagaitau.config.Properties;
import dev.valente.desafiovagaitau.domain.Statistics;
import dev.valente.desafiovagaitau.domain.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Queue;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatisticsService {

    private final TransactionService transactionService;
    private final Properties properties;

    public Statistics getStatistics() {

        var queue = transactionService.getQueue();

        var filteredList = filterList(queue);
        var summaryStatistics = calculateStatistics(filteredList);

        return createStatistics(summaryStatistics);
    }

    private List<Transaction> filterList(Queue<Transaction> queue){
        log.info("Time Window: '{}'", properties.timeWindow());
        return queue
                .stream()
                .filter(l -> Duration.between(l.getDataHora(),
                        OffsetDateTime.now(ZoneOffset.of("-03:00"))).getSeconds() <= properties.timeWindow())
                .toList();
    }

    private DoubleSummaryStatistics calculateStatistics(List<Transaction> transactions) {
        var summary = new DoubleSummaryStatistics();
        transactions.forEach(transaction -> summary.accept(transaction.getValor().doubleValue()));
        return summary;
    }

    private Statistics createStatistics(DoubleSummaryStatistics statistics) {

        var statistic = Statistics.builder()
                .sum(statistics.getSum())
                .min(statistics.getMin())
                .max(statistics.getMax())
                .average(BigDecimal.valueOf(statistics.getAverage()).setScale(2, BigDecimal.ROUND_HALF_UP))
                .count(statistics.getCount())
                .build();

        if (statistic.getCount() == 0){
            statistic.setMax(0.0);
            statistic.setMin(0.0);
        }

        return statistic;
    }

}