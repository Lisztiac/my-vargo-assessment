package com.example.danlanassessment.services;

import com.example.danlanassessment.models.Project;
import com.example.danlanassessment.repos.interfaces.ProjectRepo;
import com.example.danlanassessment.services.interfaces.ProjectService;
import com.example.danlanassessment.utils.Response;
import com.example.danlanassessment.utils.ResponseError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImp implements ProjectService {

    final private ProjectRepo projectRepo;

    @Override
    public Response<List<Project>> getProjectsByEmployeeId(Long employeeId) {
        if (employeeId == null) {
            ResponseError error = new ResponseError("id", "employee id required");
            return new Response<>(error, null);
        }
        var response = projectRepo.getProjectsByEmployeeId(employeeId);
        List<Project> projects = response.getResult() != null ? response.getResult() : new ArrayList<Project>();
        return response;
    }
}
