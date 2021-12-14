package com.example.danlanassessment.controllers;

import com.example.danlanassessment.models.Employee;
import com.example.danlanassessment.models.Project;
import com.example.danlanassessment.services.interfaces.EmployeeService;
import com.example.danlanassessment.services.interfaces.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    final private ProjectService projectService;
    final private EmployeeService employeeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/employee/projects/{id}")
    public ModelAndView getFullEmployeeProfileById(@PathVariable Long id) {

        var employeeResponse = employeeService.getEmployeeById(id);
        var projectsResponse = projectService.getProjectsByEmployeeId(id);

        ModelAndView mv = new ModelAndView("fragments/profileTable::employeeData");
        boolean isError = employeeResponse.getError() != null || projectsResponse.getError() != null;

        if (isError) {
            String message = (employeeResponse.getError() != null) ?
                             employeeResponse.getError().getMessage() :
                             projectsResponse.getError().getMessage();

            mv.addObject("responseError", message);
            return mv;
        }
        Employee retrievedEmployee = employeeResponse.getResult();
        List<Project> projects = projectsResponse.getResult();
        retrievedEmployee.setProjects(projects);
        mv.addObject("employee", retrievedEmployee);

        if (projects.isEmpty()) {
            mv.addObject("responseError", "no projects found");
        }
        return mv;
    }
}
