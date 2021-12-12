package com.example.danlanassessment.services.interfaces;

import com.example.danlanassessment.models.Project;
import com.example.danlanassessment.utils.Response;

import java.util.List;

public interface ProjectService {
    Response<List<Project>> getProjectsByEmployeeId(Long employeeId);
}
