package com.example.danlanassessment.repos.interfaces;

import com.example.danlanassessment.models.Employee;
import com.example.danlanassessment.utils.Response;

public interface EmployeeRepo {
    Response<Employee> getEmployeeById(Long employeeId);
}
