package com.example.danlanassessment.services;

import com.example.danlanassessment.models.Employee;
import com.example.danlanassessment.repos.interfaces.EmployeeRepo;
import com.example.danlanassessment.services.interfaces.EmployeeService;
import com.example.danlanassessment.utils.Response;
import com.example.danlanassessment.utils.ResponseError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeServiceImpTest {

    private final EmployeeRepo employeeRepo = Mockito.mock(EmployeeRepo.class);
    private EmployeeService employeeService;

    @BeforeEach
    void setup() {
        employeeService = new EmployeeServiceImp(employeeRepo);
    }

    @Test
    void getEmployeeById_NullId_ReturnsError() {
        // Arrange
        String expected = "employee id required";

        // Act
        Response<Employee> response = employeeService.getEmployeeById(null);

        // Assert
        ResponseError error = response.getError();

        assertThat(response.getResult()).isNull();
        assertThat(error).isNotNull();
        assertThat(error.getMessage()).isEqualTo(expected);
    }

    @Test
    void getEmployeeById_ValidId_ReturnsEmployee() {
        // Arrange
        var employee = new Employee(4L, "dan", "lan", "software developer", "uwm", null);
        var expected = new Response<>(null, employee);
        when(employeeRepo.getEmployeeById(any(Long.class))).thenReturn(expected);

        // Act
        Response<Employee> response = employeeService.getEmployeeById(employee.getId());

        // Assert
        var retrievedEmployee = response.getResult();

        assertThat(response.getError()).isNull();
        assertThat(response.getResult()).isNotNull();
        assertThat(employee).isEqualTo(retrievedEmployee).usingRecursiveComparison();
        verify(employeeRepo).getEmployeeById(employee.getId());
    }
}
