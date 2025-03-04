package dev.valente.desafiovagaitau.service;

import dev.valente.desafiovagaitau.config.Properties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static dev.valente.desafiovagaitau.utils.StatisticsUtil.EMPTY_QUEUE;
import static dev.valente.desafiovagaitau.utils.StatisticsUtil.QUEUE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StatisticsServiceTest {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private TransactionService transactionService;

    @Mock
    private Properties properties;

    @Test
    @Order(1)
    @DisplayName("Should return statistics successfully")
    void getStatistics_shouldReturnStatistics_WhenSuccessful() {
        BDDMockito.when(transactionService.getQueue()).thenReturn(QUEUE);
        BDDMockito.when(properties.timeWindow()).thenReturn(60);

        var sut = statisticsService.getStatistics();

        Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("sum", 150.0)
                .hasFieldOrPropertyWithValue("average", new BigDecimal("75.00"))
                .hasFieldOrPropertyWithValue("min", 50.0)
                .hasFieldOrPropertyWithValue("max", 100.0)
                .hasFieldOrPropertyWithValue("count", 2L)
                .hasNoNullFieldsOrProperties();
    }

    @Test
    @Order(1)
    @DisplayName("Should return statistics successfully")
    void getStatistics_shouldReturnStatisticsWithValue0_WhenSuccessful() {
        BDDMockito.when(transactionService.getQueue()).thenReturn(EMPTY_QUEUE);
        BDDMockito.when(properties.timeWindow()).thenReturn(60);

        var sut = statisticsService.getStatistics();

        Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("sum", 0.0)
                .hasFieldOrPropertyWithValue("average", new BigDecimal("0.00"))
                .hasFieldOrPropertyWithValue("min", 0.0)
                .hasFieldOrPropertyWithValue("max", 0.0)
                .hasFieldOrPropertyWithValue("count", 0L)
                .hasNoNullFieldsOrProperties();
    }

}