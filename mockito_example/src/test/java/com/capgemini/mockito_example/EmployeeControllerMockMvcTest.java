package com.capgemini.mockito_example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerMockMvcTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Should handle request validation errors")
	void testValidationError() throws Exception {
		mockMvc.perform(
				post("/employees").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"\",\"basic\":5000}")) // Empty
																														// name
																														// field
				.andExpect(status().isBadRequest());
	}
}
