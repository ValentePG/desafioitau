package dev.valente.desafiovagaitau;

import org.springframework.stereotype.Repository;

import java.util.DoubleSummaryStatistics;

@Repository
public class StatisticsRepository {

    private final DoubleSummaryStatistics statistics = new DoubleSummaryStatistics();

    public void addTransaction(double value) {
        synchronized (statistics) {
            statistics.accept(value);
        }
    }

    public DoubleSummaryStatistics getStatistics() {
        synchronized (statistics) {
            return new DoubleSummaryStatistics() {{
                combine(statistics);
            }};
        }
    }
}
