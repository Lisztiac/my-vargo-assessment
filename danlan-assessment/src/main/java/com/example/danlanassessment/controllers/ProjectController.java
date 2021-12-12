package com.example.danlanassessment.controllers;

import com.example.danlanassessment.models.Project;
import com.example.danlanassessment.services.interfaces.ProjectService;
import com.example.danlanassessment.utils.Response;
import com.example.danlanassessment.utils.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/{employeeId}")
    public Response<List<Project>> getProjectsByEmployeeId(@PathVariable Long employeeId) {
        if (employeeId == null) {
            ResponseError error = new ResponseError("id", "employee id required");
            return new Response<>(error, null);
        }
        return projectService.getProjectsByEmployeeId(employeeId);
    }
}
