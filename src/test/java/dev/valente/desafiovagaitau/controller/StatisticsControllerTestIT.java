package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.config.IntegrationTestsConfig;
import dev.valente.desafiovagaitau.config.Properties;
import dev.valente.desafiovagaitau.config.RestAssuredConfig;
import dev.valente.desafiovagaitau.repository.TransactionRepository;
import dev.valente.desafiovagaitau.utils.FileUtils;
import dev.valente.desafiovagaitau.utils.StatisticsUtil;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static dev.valente.desafiovagaitau.utils.StatisticsUtil.QUEUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RestAssuredConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StatisticsControllerTestIT extends IntegrationTestsConfig {

    @Autowired
    private RequestSpecification requestSpecification;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private StatisticsUtil statisticsUtil;

    @MockitoSpyBean
    private Properties properties;

    @MockitoSpyBean
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        RestAssured.requestSpecification = requestSpecification;
        statisticsUtil.initiateQueue();
    }

    @AfterEach
    void tearDown() {
        statisticsUtil.clearQueue();
    }

    @Test
    @Order(1)
    @DisplayName("GET /estatistica should return statistics with timeWindow 60")
    void getStatistics_ShouldReturnStatistics_WithTimeWindow60() {

        BDDMockito.when(transactionRepository.getQueue()).thenReturn(QUEUE);
        BDDMockito.when(properties.timeWindow()).thenReturn(60);

        var response = fileUtils.readFile("/responses/get_getstatistics60_200.json");

        RestAssured.given()
                .get("/estatistica")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(Matchers.equalTo(response))
                .log().all();

    }

    @Test
    @Order(2)
    @DisplayName("GET /estatistica should return statistics with timeWindow 90")
    void getStatistics_ShouldReturnStatistics_WithTimeWindow90() {

        BDDMockito.when(transactionRepository.getQueue()).thenReturn(QUEUE);
        BDDMockito.when(properties.timeWindow()).thenReturn(90);

        var response = fileUtils.readFile("/responses/get_getstatistics90_200.json");

        RestAssured.given()
                .get("/estatistica")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(Matchers.equalTo(response))
                .log().all();

    }

    @Test
    @Order(3)
    @DisplayName("GET /estatistica should return statistics with timeWindow 120")
    void getStatistics_ShouldReturnStatistics_WithTimeWindow120() {

        BDDMockito.when(transactionRepository.getQueue()).thenReturn(QUEUE);
        BDDMockito.when(properties.timeWindow()).thenReturn(120);

        var response = fileUtils.readFile("/responses/get_getstatistics120_200.json");

        RestAssured.given()
                .get("/estatistica")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(Matchers.equalTo(response))
                .log().all();

    }

    @Test
    @Order(4)
    @DisplayName("GET /estatistica should return empty statistics when not have any statistics to show")
    void getStatistics_ShouldReturnEmptyStatistics_WhenSuccessfull() {

        var response = fileUtils.readFile("/responses/get_getstatisticsempty_200.json");

        RestAssured.given()
                .get("/estatistica")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body(Matchers.equalTo(response))
                .log().all();

    }
}
