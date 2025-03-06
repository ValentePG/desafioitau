package dev.valente.desafiovagaitau.controller;

import dev.valente.desafiovagaitau.config.IntegrationTestsConfig;
import dev.valente.desafiovagaitau.utils.FileUtils;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import net.javacrumbs.jsonunit.assertj.JsonAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

class TransactionControllerTestIT extends IntegrationTestsConfig {

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
    @DisplayName("POST /transacao Should return created 201 when transaction was save successfully")
    void saveTransaction_ShouldReturnCreated_whenSuccessfully() {

        var request = fileUtils.readFile("requests/post_savetransaction_201.json");

        RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(request)
                .post("/transacao")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .log().all();

    }

    @Test
    @Order(2)
    @DisplayName("POST /transacao Should return unprocessable entity 422 when some of the fields are incorrectly filled")
    void saveTransaction_ShouldReturnUnprocessableEntity_whenSomeOfTheFieldsAreIncorrectlyFilled() {

        var request = fileUtils.readFile("requests/post_savetransaction_422.json");

        var responseFromFile = fileUtils.readFile("responses/post_savetransaction_422.json");

        var response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(request)
                .post("/transacao")
                .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .log().all()
                .extract().response().asString();

        JsonAssertions.assertThatJson(response)
                .whenIgnoringPaths("timestamp")
                .isEqualTo(responseFromFile);
    }

    @Test
    @Order(3)
    @DisplayName("POST /transacao Should return bad request 400 when given JSON is invalid")
    void saveTransaction_ShouldReturnBadRequest_whenGivenJsonIsInvalid() {

        var request = fileUtils.readFile("requests/post_savetransaction_400.json");

        var responseFromFile = fileUtils.readFile("responses/post_savetransaction_400.json");

        var response = RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .body(request)
                .post("/transacao")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .log().all()
                .extract().response().asString();

        JsonAssertions.assertThatJson(response)
                .whenIgnoringPaths("timestamp")
                .isEqualTo(responseFromFile);

    }

    @Test
    @Order(4)
    @DisplayName("DELETE /transacao Should return ok 200 when transaction was deleted successfully")
    void deleteTransaction_ShouldReturnOk_whenSuccessfully() {

        RestAssured.given()
                .contentType("application/json")
                .accept("application/json")
                .delete("/transacao")
                .then()
                .statusCode(HttpStatus.OK.value())
                .log().all();

    }
}