package com.venkuuz.catalog_service.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
    properties = {
      "spring.test.database.replace=none",
      "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
    })
@Sql(value = "/test-data.sql")
class ProductRepositoryTest {

  @Autowired private ProductRepository productRepository;

  @Test
  void shouldGetAllProducts() {
    List<ProductEntity> list = productRepository.findAll();
    assertThat(list).hasSize(15);
  }

  @Test
  void shouldGetproductByCode() {
    ProductEntity product = productRepository.findByCode("P100").orElseThrow();
    assertThat(product.getCode()).isEqualTo("P100");
    assertThat(product.getName()).isEqualTo("The Hunger Games");
  }
}
