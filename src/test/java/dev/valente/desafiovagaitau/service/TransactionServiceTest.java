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


    }
}