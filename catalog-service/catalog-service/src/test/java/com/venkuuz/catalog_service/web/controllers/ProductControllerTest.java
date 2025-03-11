package com.venkuuz.catalog_service.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.venkuuz.catalog_service.AbstractIntegrationTest;
import com.venkuuz.catalog_service.domain.Product;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "/test-data.sql")
public class ProductControllerTest extends AbstractIntegrationTest {

  @Test
  public void shouldReturnProduct() {
    System.out.println(RestAssured.port);
    given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/products")
        .then()
        .statusCode(200)
        .body("data", hasSize(10))
        .body("totalElements", is(15))
        .body("pageNumber", is(1))
        .body("totalPages", is(2))
        .body("isFirst", is(true))
        .body("isLast", is(false))
        .body("hasNext", is(true))
        .body("hasPrevious", is(false));
  }

  @Test
  void shouldGetProductByCode() {
    Product product =
        given()
            .contentType(ContentType.JSON)
            .when()
            .get("/api/products/{code}", "P100")
            .then()
            .statusCode(200)
            .extract()
            .body()
            .as(Product.class);

    assertThat(product.code()).isEqualTo("P100");
    assertThat(product.name()).isEqualTo("The Hunger Games");
  }

  @Test
  void shouldReturnNotFoundWhenProductNotExists() {
    String code = "invalid_code";
    given()
        .contentType(ContentType.JSON)
        .when()
        .get("/api/products/{code}", code)
        .then()
        .statusCode(404)
        .body("status", is(404))
        .body("title", is("Product Not Found"));
  }
}
