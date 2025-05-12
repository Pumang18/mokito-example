package com.capgemini.mockito_example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceIntegrationTest {

	@Autowired
	private EmployeeService employeeService;

	@Test
	@DisplayName("Should save and retrieve an Employee")
	void testSaveAndRetrieveEmployee() {
		Employee emp = new Employee(null, "Alice", 5000);
		Employee savedEmp = employeeService.saveEmployee(emp);

		Optional<Employee> retrievedEmp = employeeService.getEmployeeById(savedEmp.getCode());
		assertTrue(retrievedEmp.isPresent());
		assertEquals("Alice", retrievedEmp.get().getName());
	}
}