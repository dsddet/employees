package com.dsddet.employees;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = EmployeesApplication.class)
@PropertySource( "classpath:test-applicaton.properties")
class EmployeesApplicationTests {

	@Test
	void contextLoads() {
	}

}
