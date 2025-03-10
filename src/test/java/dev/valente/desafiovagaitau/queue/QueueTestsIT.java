package dev.valente.desafiovagaitau.queue;

import dev.valente.desafiovagaitau.config.IntegrationTestsConfig;
import dev.valente.desafiovagaitau.repository.TransactionRepository;
import dev.valente.desafiovagaitau.service.TransactionService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import static dev.valente.desafiovagaitau.utils.StatisticsUtil.TRANSACTION;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueueTestsIT extends IntegrationTestsConfig {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @AfterEach
    public void cleanUp() {
        transactionRepository.clearAll();
    }

    @Test
    @Order(1)
    @DisplayName("getQueue should return a queue with 3 values")
    void getQueue_ShouldReturnAQueueWith3values_WhenSuccessfull() {

        var executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(
                () -> transactionService.saveTransaction(TRANSACTION)
                , executorService);

        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(
                () -> transactionService.saveTransaction(TRANSACTION)
                , executorService);

        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(
                () -> transactionService.saveTransaction(TRANSACTION)
                , executorService);

        CompletableFuture.allOf(completableFuture, completableFuture2, completableFuture3).join();

        executorService.shutdown();

        Assertions.assertThat(transactionRepository.getQueue())
                .hasSize(3)
                .contains(TRANSACTION);

    }

    @Test
    @Order(2)
    @DisplayName("getQueue should return a queue with no values")
    void getQueue_ShouldReturnAQueueWithNoValues_WhenClearAllIsUsed() {

        getQueue_ShouldReturnAQueueWith3values_WhenSuccessfull();

        transactionService.clearAll();

        Assertions.assertThat(transactionRepository.getQueue())
                .isEmpty();
    }

}
