package dev.valente.desafiovagaitau.service;

import dev.valente.desafiovagaitau.config.Properties;
import dev.valente.desafiovagaitau.utils.StatisticsUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static dev.valente.desafiovagaitau.utils.StatisticsUtil.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StatisticsServiceTest {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private TransactionService transactionService;

    @Mock
    private Properties properties;

    private final StatisticsUtil statisticsUtil = new StatisticsUtil();

    @BeforeEach
    void setUp() {
        statisticsUtil.initiateQueue();
    }

    @AfterEach
    void tearDown() {
        statisticsUtil.clearQueue();
    }

    @Test
    @Order(1)
    @DisplayName("Should return statistics successfully with time window 60")
    void getStatistics_shouldReturnStatistics_WithTimeWindow60() {
        BDDMockito.when(transactionService.getQueue()).thenReturn(QUEUE);
        BDDMockito.when(properties.timeWindow()).thenReturn(60);

        var sut = statisticsService.getStatistics();

        Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("sum", STATISTICS_FOR_TIMEWINDOW60.sum())
                .hasFieldOrPropertyWithValue("average", STATISTICS_FOR_TIMEWINDOW60.average())
                .hasFieldOrPropertyWithValue("min", STATISTICS_FOR_TIMEWINDOW60.min())
                .hasFieldOrPropertyWithValue("max", STATISTICS_FOR_TIMEWINDOW60.max())
                .hasFieldOrPropertyWithValue("count", STATISTICS_FOR_TIMEWINDOW60.count());

        BDDMockito.verify(transactionService, BDDMockito.times(1)).getQueue();
        BDDMockito.verifyNoMoreInteractions(transactionService);
        BDDMockito.verify(properties, BDDMockito.times(QUEUE.size())).timeWindow();
        BDDMockito.verifyNoMoreInteractions(properties);

    }

    @Test
    @Order(2)
    @DisplayName("Should return statistics successfully with time window 90")
    void getStatistics_shouldReturnStatistics_withTimeWindow90() {
        BDDMockito.when(transactionService.getQueue()).thenReturn(QUEUE);
        BDDMockito.when(properties.timeWindow()).thenReturn(90);

        var sut = statisticsService.getStatistics();

        Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("sum", STATISTICS_FOR_TIMEWINDOW90.sum())
                .hasFieldOrPropertyWithValue("average", STATISTICS_FOR_TIMEWINDOW90.average())
                .hasFieldOrPropertyWithValue("min", STATISTICS_FOR_TIMEWINDOW90.min())
                .hasFieldOrPropertyWithValue("max", STATISTICS_FOR_TIMEWINDOW90.max())
                .hasFieldOrPropertyWithValue("count", STATISTICS_FOR_TIMEWINDOW90.count());

        BDDMockito.verify(transactionService, BDDMockito.times(1)).getQueue();
        BDDMockito.verifyNoMoreInteractions(transactionService);
        BDDMockito.verify(properties, BDDMockito.times(QUEUE.size())).timeWindow();
        BDDMockito.verifyNoMoreInteractions(properties);
    }

    @Test
    @Order(3)
    @DisplayName("Should return statistics successfully with time window 120")
    void getStatistics_shouldReturnStatistics_withTimeWindow120() {
        BDDMockito.when(transactionService.getQueue()).thenReturn(QUEUE);
        BDDMockito.when(properties.timeWindow()).thenReturn(120);

        var sut = statisticsService.getStatistics();

        Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("sum", STATISTICS_FOR_TIMEWINDOW120.sum())
                .hasFieldOrPropertyWithValue("average", STATISTICS_FOR_TIMEWINDOW120.average())
                .hasFieldOrPropertyWithValue("min", STATISTICS_FOR_TIMEWINDOW120.min())
                .hasFieldOrPropertyWithValue("max", STATISTICS_FOR_TIMEWINDOW120.max())
                .hasFieldOrPropertyWithValue("count", STATISTICS_FOR_TIMEWINDOW120.count());

        BDDMockito.verify(transactionService, BDDMockito.times(1)).getQueue();
        BDDMockito.verifyNoMoreInteractions(transactionService);
        BDDMockito.verify(properties, BDDMockito.times(QUEUE.size())).timeWindow();
        BDDMockito.verifyNoMoreInteractions(properties);
    }


    @Test
    @Order(4)
    @DisplayName("Should return statistics successfully when have no transaction in the time window")
    void getStatistics_shouldReturnStatisticsWithValue0_WhenSuccessful() {
        BDDMockito.when(transactionService.getQueue()).thenReturn(EMPTY_QUEUE);

        var sut = statisticsService.getStatistics();

        Assertions.assertThat(sut)
                .hasFieldOrPropertyWithValue("sum", 0.0)
                .hasFieldOrPropertyWithValue("average", 0.0)
                .hasFieldOrPropertyWithValue("min", 0.0)
                .hasFieldOrPropertyWithValue("max", 0.0)
                .hasFieldOrPropertyWithValue("count", 0L);

    }

}