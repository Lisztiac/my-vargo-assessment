package com.example.danlanassessment.controllers;

import com.example.danlanassessment.models.Employee;
import com.example.danlanassessment.models.Project;
import com.example.danlanassessment.services.interfaces.EmployeeService;
import com.example.danlanassessment.services.interfaces.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/employee/projects")
    public ModelAndView getFullEmployeeProfileById(@RequestBody Employee employee) {
        Long id = employee.getId();
        var employeeResponse = employeeService.getEmployeeById(id);
        var projectsResponse = projectService.getProjectsByEmployeeId(id);
        ModelAndView mv = new ModelAndView("index::employeeData");

        if (projectsResponse.getError() != null) {
            String message = projectsResponse.getError().getMessage();
            mv.addObject("responseError", message);
            return mv;
        }
        if (employeeResponse.getError() != null) {
            String message = employeeResponse.getError().getMessage();
            mv.addObject("responseError", message);
            return mv;
        }

        Employee retrievedEmployee = employeeResponse.getResult();
        List<Project> projects = projectsResponse.getResult();
        retrievedEmployee.setProjects(projects);
        mv.addObject("employee", retrievedEmployee);
        return mv;
    }
}
