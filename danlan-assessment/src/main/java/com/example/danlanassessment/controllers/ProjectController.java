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

    @GetMapping("/{id}")
    public Response<List<Project>> getProjectsByEmployeeId(@PathVariable String id) {
        if (id == null) {
            ResponseError error = new ResponseError("id", "employee id required");
            return new Response<>(error, null);
        }
        try {
            Long employeeId = Long.parseLong(id);
            return projectService.getProjectsByEmployeeId(employeeId);
        }
        catch (NumberFormatException e) {
            ResponseError error = new ResponseError("id", "id must be an integer");
            return new Response<>(error, null);
        }
    }
}
