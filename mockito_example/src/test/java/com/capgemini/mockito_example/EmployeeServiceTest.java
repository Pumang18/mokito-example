package com.capgemini.mockito_example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeService employeeService;

	@Test
	@DisplayName("Should save Employee and return the saved instance")
	void shouldSaveEmployee() {
		Employee emp = new Employee(1L, "Alice", 5000);
		Mockito.when(employeeRepository.save(emp)).thenReturn(emp);

		Employee savedEmp = employeeService.saveEmployee(emp);

		assertNotNull(savedEmp, "Saved employee should not be null");
		assertEquals(1L, savedEmp.getCode(), "Employee ID should match");
		assertEquals("Alice", savedEmp.getName(), "Employee name should match");
		assertEquals(5000, savedEmp.getBasic(), "Employee salary should match");
	}

	@Test
	@DisplayName("Should find Employee by ID successfully")
	void shouldFindEmployeeById() {
		Employee emp = new Employee(1L, "Alice", 5000);
		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));

		Optional<Employee> foundEmp = employeeService.getEmployeeById(1L);

		assertTrue(foundEmp.isPresent(), "Employee should be present");
		assertEquals("Alice", foundEmp.get().getName(), "Employee name should match");
		assertEquals(5000, foundEmp.get().getBasic(), "Employee salary should match");
	}

}
