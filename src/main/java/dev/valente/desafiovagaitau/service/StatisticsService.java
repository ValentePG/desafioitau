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

    private List<Transaction> filterList(Queue<Transaction> queue) {
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

    private Statistics createStatistics(DoubleSummaryStatistics summaryStatistics) {
        var sum = BigDecimal.valueOf(summaryStatistics.getSum()).setScale(2).stripTrailingZeros();
        var avg = BigDecimal.valueOf(summaryStatistics.getAverage()).setScale(3).stripTrailingZeros();
        var count = summaryStatistics.getCount();

        var statistics = Statistics.builder()
                .average(avg.doubleValue())
                .sum(sum.doubleValue())
                .count(count)
                .build();

        return validateMinMax(summaryStatistics, statistics);

    }

    private Statistics validateMinMax(DoubleSummaryStatistics summaryStatistics, Statistics statistics) {
        var count = summaryStatistics.getCount();
        var max = (count == 0) ? BigDecimal.ZERO : BigDecimal
                .valueOf(summaryStatistics.getMax()).setScale(2).stripTrailingZeros();
        var min = (count == 0) ? BigDecimal.ZERO : BigDecimal
                .valueOf(summaryStatistics.getMin()).setScale(2).stripTrailingZeros();

        statistics.setMax(max.doubleValue());
        statistics.setMin(min.doubleValue());

        return statistics;

    }

}