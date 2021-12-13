package com.example.danlanassessment.services;

import com.example.danlanassessment.models.Employee;
import com.example.danlanassessment.repos.interfaces.EmployeeRepo;
import com.example.danlanassessment.services.interfaces.EmployeeService;
import com.example.danlanassessment.utils.Response;
import com.example.danlanassessment.utils.ResponseError;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    final private EmployeeRepo employeeRepo;

    @Override
    public Response<Employee> getEmployeeById(Long employeeId) {
        if (employeeId == null) {
            ResponseError error = new ResponseError("id", "employee id required");
            return new Response<>(error, null);
        }
        return employeeRepo.getEmployeeById(employeeId);
    }
}
