package com.capgemini.mockito_example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

@SpringBootTest
class EmployeeSpyTest {

	@MockitoSpyBean
	private EmployeeService employeeService;

	@MockitoBean
	private EmployeeRepository employeeRepository;

	@Test
	@DisplayName("Should verify spy behavior on EmployeeService")
	void shouldSpyService() {
		Employee emp = new Employee(1L, "Alice", 5000);
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);

		Employee savedEmp = employeeService.saveEmployee(emp);

		Mockito.verify(employeeService).saveEmployee(emp); // Verify actual method execution
		assertNotNull(savedEmp, "Saved employee should not be null");
		assertEquals("Alice", savedEmp.getName(), "Employee name should match");
	}
}