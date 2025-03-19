package com.srinivas.bookstore.catalog.web.controllers;

import com.srinivas.bookstore.catalog.AbstractIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


class ProductControllerTest extends AbstractIntegrationTest {
    @Test
    public void shouldReturnPaginatedProducts() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(RestAssured.baseURI + ":" + RestAssured.port + "/api/products?page=1&size=5")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON)
                .body("data", hasSize(5))
                .body("totalElements", equalTo(15))
                .body("totalPages", equalTo(3))
                .body("hasNext", equalTo(true))
                .body("hasPrev", equalTo(false))
                .body("isFirst", equalTo(false))
                .body("isLast", equalTo(true))
                .body("first", equalTo(false))
                .body("last", equalTo(true));
    }
}
