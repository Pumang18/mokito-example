package com.capgemini.mockito_example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//Used for database-related tests with an in-memory H2 database.

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Uses real DB instead of H2
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Should persist Employee into DB")
    void testPersistEmployee() {
        Employee emp = new Employee(null, "Charlie", 7000);
        Employee savedEmp = employeeRepository.save(emp);

        Optional<Employee> foundEmp = employeeRepository.findById(savedEmp.getCode());
        assertTrue(foundEmp.isPresent());
    }
}