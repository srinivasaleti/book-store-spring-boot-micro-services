package com.srinivas.bookstore.catalog.web.controllers;

import com.srinivas.bookstore.catalog.AbstractIntegrationTest;
import com.srinivas.bookstore.catalog.domain.ProductEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.*;


@Sql("/test-data.sql")
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
                .body("totalElements", equalTo(10))
                .body("totalPages", equalTo(2))
                .body("hasNext", equalTo(true))
                .body("hasPrev", equalTo(false))
                .body("isFirst", equalTo(false))
                .body("isLast", equalTo(true))
                .body("first", equalTo(false))
                .body("last", equalTo(true));
    }

    @Test
    public void shouldReturnProductByCode() {
        ProductEntity product = given()
                .contentType(ContentType.JSON)
                .when()
                .get("api/products/P001")
                .then()
                .statusCode(HttpStatus.OK.value())
                .assertThat()
                .extract()
                .body()
                .as(ProductEntity.class);

        assertThat(product.getCode()).isEqualTo("P001");
    }

    @Test
    public void shouldReturnNotFoundIfProductNotFound() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("api/products/Not_Found")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", is(404));
    }
}
