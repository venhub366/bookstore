package com.venkuuz.catalog_service;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class CatalogServiceApplicationTests {

	@Ignore
	void contextLoads() {
	}

}
