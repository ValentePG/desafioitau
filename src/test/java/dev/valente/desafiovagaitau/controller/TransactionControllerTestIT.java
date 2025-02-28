package dev.valente.desafiovagaitau.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.valente.desafiovagaitau.config.RestAssuredConfig;
import dev.valente.desafiovagaitau.dto.TransactionDTO;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RestAssuredConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RequestSpecification requestSpecification;

    @BeforeEach
    void setUp() {
        RestAssured.requestSpecification = requestSpecification;
    }

    @Test
    @Order(1)
    @DisplayName("Should return created 201 when transaction was save successfully")
    void salvarTransacao_ShouldReturnCreated_whenSuccessfully() throws JsonProcessingException {

        TransactionDTO dto = TransactionDTO.builder()
                .valor(BigDecimal.valueOf(50.00))
                .dataHora(OffsetDateTime.now()).build();

        var json = objectMapper.writeValueAsString(dto);
        var count = 10;
        while (count > 0){
            RestAssured.given()
                    .contentType("application/json")
                    .accept("application/json")
                    .body(json)
                    .post("/transacao")
                    .then()
                    .statusCode(201)
                    .log().all();
            count--;
        }

    }
}