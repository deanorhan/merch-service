package org.daemio.merch.controller;

import static org.hamcrest.Matchers.*;

import org.daemio.merch.MerchServiceApplication;
import org.daemio.merch.model.Merch;
import org.daemio.merch.repository.MerchRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import io.restassured.http.ContentType;

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
            .contentType(ContentType.JSON)
            .body("merch", hasSize(1));
    }

    @Test
    public void whenCalling_thenReturnNoResults() {
        given()
            .port(port)
            .queryParam("page", 2)
        .when()
            .get("/merch")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(ContentType.JSON)
            .body("merch", hasSize(0));
    }

    @Test
    public void whenCallingForMerch_thenGetMerchItem() {
        given()
            .port(port)
        .when()
            .get("/merch/5")
        .then()
            .statusCode(HttpStatus.OK.value())
            .contentType(ContentType.JSON)
            .body("id", equalTo(5));
    }
}
