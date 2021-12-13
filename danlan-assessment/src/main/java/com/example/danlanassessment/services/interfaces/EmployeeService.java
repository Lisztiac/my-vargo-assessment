package com.example.danlanassessment.services.interfaces;

import com.example.danlanassessment.models.Employee;
import com.example.danlanassessment.utils.Response;

public interface EmployeeService {
    Response<Employee> getEmployeeById(Long employeeId);
}
