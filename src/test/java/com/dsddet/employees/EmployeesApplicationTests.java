package com.dsddet.employees;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = EmployeesApplication.class)
@TestPropertySource(locations = "classpath:test-applicaton.properties")
class EmployeesApplicationTests {

	@Test
	void contextLoads() {
	}

}
