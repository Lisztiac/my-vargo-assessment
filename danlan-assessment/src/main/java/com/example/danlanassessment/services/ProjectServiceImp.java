package com.example.danlanassessment.services;

import com.example.danlanassessment.models.Project;
import com.example.danlanassessment.repos.interfaces.ProjectRepo;
import com.example.danlanassessment.services.interfaces.ProjectService;
import com.example.danlanassessment.utils.Response;
import com.example.danlanassessment.utils.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImp implements ProjectService {
    @Autowired
    ProjectRepo projectRepo;

    @Override
    public Response<List<Project>> getProjectsByEmployeeId(Long employeeId) {
        if (employeeId == null) {
            ResponseError error = new ResponseError("id", "employee id required");
            return new Response<>(error, null);
        }
        var response = projectRepo.getProjectsByEmployeeId(employeeId);
        List<Project> projects = response.getResult();

        if (projects.isEmpty()) {
            ResponseError error = new ResponseError("id", "no projects found");
            return new Response<>(error, null);
        }
        return response;
    }
}
