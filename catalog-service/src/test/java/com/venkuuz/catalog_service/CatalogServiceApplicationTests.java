package com.venkuuz.catalog_service;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
public class CatalogServiceApplicationTests {

  @Test
  public void contextLoads() {}
}
