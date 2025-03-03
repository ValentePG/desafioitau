package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.config.RestAssuredConfig;
import dev.valente.desafiovagaitau.utils.FileUtils;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RestAssuredConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionControllerTestIT {

    @Autowired
    private RequestSpecification requestSpecification;

    @Autowired
    private FileUtils fileUtils;

    @BeforeEach
    void setUp() {
        RestAssured.requestSpecification = requestSpecification;
    }

    @Test
    @Order(1)
    @DisplayName("Should return created 201 when transaction was save successfully")
    void saveTransaction_ShouldReturnCreated_whenSuccessfully() {

        var request = fileUtils.readFile("requests/post_savetransaction_201.json");

        RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(request)
                .post("/transacao")
                .then()
                .statusCode(201)
                .log().all();

    }
}