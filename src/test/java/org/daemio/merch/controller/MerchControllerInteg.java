package org.daemio.merch.controller;

import org.daemio.merch.MerchServiceApplication;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.*;

@SpringBootTest(
    classes = MerchServiceApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT
)
@Disabled
public class MerchControllerInteg {
    
    @LocalServerPort
    private int port;

    @Test
    public void whenCallingThenReturn() {
        given()
            .port(port)
        .when()
            .get("/merch")
        .then()
            .statusCode(HttpStatus.NOT_IMPLEMENTED.value());
    }
}
