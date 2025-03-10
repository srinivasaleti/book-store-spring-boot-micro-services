package com.srinivas.bookstore.catalog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.Assert.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class CatalogServiceApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(1, 1);
	}

}
