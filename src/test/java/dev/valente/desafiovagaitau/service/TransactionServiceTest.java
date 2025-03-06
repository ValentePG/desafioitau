package dev.valente.desafiovagaitau.service;

import dev.valente.desafiovagaitau.repository.TransactionRepository;
import dev.valente.desafiovagaitau.utils.StatisticsUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static dev.valente.desafiovagaitau.utils.StatisticsUtil.QUEUE;
import static dev.valente.desafiovagaitau.utils.StatisticsUtil.TRANSACTION;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

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
    @DisplayName("save transaction should save transaction when sucessfull")
    void saveTransaction_ShouldSaveTransaction_WhenSuccessfull() {

        transactionService.saveTransaction(TRANSACTION);

        BDDMockito.verify(transactionRepository, BDDMockito.times(1))
                .saveTransaction(TRANSACTION);
        BDDMockito.verifyNoMoreInteractions(transactionRepository);
    }

    @Test
    @Order(2)
    @DisplayName("get queue should return a queue")
    void getQueue_ShouldReturnQueue_WhenSuccessfull() {
        BDDMockito.when(transactionRepository.getQueue()).thenReturn(QUEUE);

        var sut = transactionService.getQueue();

        Assertions.assertThat(sut).isEqualTo(QUEUE);

        BDDMockito.verify(transactionRepository, BDDMockito.times(1)).getQueue();
    }

    @Test
    @Order(3)
    @DisplayName("clear all should clear queue")
    void clearQueue_ShouldClearQueue_WhenSuccessfull() {

        transactionService.clearAll();

        BDDMockito.verify(transactionRepository, BDDMockito.times(1)).clearAll();
    }
}