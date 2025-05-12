package com.capgemini.mockito_example;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private EmployeeService employeeService;

	@Test
	@DisplayName("Should successfully create an Employee")
	void shouldCreateEmployee() throws Exception {
		Employee emp = new Employee(1L, "Alice", 5000);
		Mockito.when(employeeService.saveEmployee(Mockito.any())).thenReturn(emp);

		mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Alice\",\"basic\":5000}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Alice")).andExpect(jsonPath("$.basic").value(5000));
	}

	@Test
	@DisplayName("Should fetch Employee data by ID")
	void shouldFetchEmployeeById() throws Exception {
		Employee emp = new Employee(1L, "Alice", 5000);
		Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(emp));

		mockMvc.perform(get("/employees/1")).andExpect(status().isOk()).andExpect(jsonPath("$.code").value(1))
				.andExpect(jsonPath("$.name").value("Alice")).andExpect(jsonPath("$.basic").value(5000)).andDo(MockMvcResultHandlers.print());
	}
}