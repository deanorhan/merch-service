package org.daemio.merch.controller;

import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;

import org.daemio.merch.MerchServiceApplication;
import org.daemio.merch.domain.Merch;
import org.daemio.merch.repository.MerchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;

@SpringBootTest(
    classes = MerchServiceApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@ActiveProfiles("integ-test")
public class MerchControllerAPITest {
    
    @LocalServerPort
    private int port;

    @Autowired
    private MerchRepository merchRepository;

    @BeforeEach
    public void setup() {
        Merch merch = new Merch();
        merch.setId(5);
        merch.setTitle("Amazing band shirt");
        merch.setPrice(BigDecimal.valueOf(5));

        merchRepository.save(merch);
    }

    @AfterEach
    public void tearDown() {
        merchRepository.deleteAll();
    }

    @Test
    public void whenCalling_thenReturn() {
        given()
            .port(port)
        .when()
            .get("/merch")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("merch", hasSize(1))
            .body("page", is(0))
            .body("size", is(25))
            .body("totalPages", is(1));
    }

    @Test
    public void whenCalling_thenReturnNoResults() {
        var page = 2;

        given()
            .port(port)
            .queryParam("page", page)
        .when()
            .get("/merch")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("merch", hasSize(0))
            .body("page", is(page))
            .body("size", is(25))
            .body("totalPages", is(1));
    }

    @Test
    public void whenCallingForMerch_thenGetMerchItem() {
        var merchId = 5;
        
        given()
            .port(port)
            .pathParam("merchId", merchId)
        .when()
            .get("/merch/{merchId}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("id", is(merchId));
    }

    @Test
    public void givenMerchNotThere_whenCallingForMerch_thenGetNotFound() {
        var merchId = 17;
        
        given()
            .port(port)
            .pathParam("merchId", merchId)
        .when()
            .get("/merch/{merchId}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value())
            .contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE);
    }
}
