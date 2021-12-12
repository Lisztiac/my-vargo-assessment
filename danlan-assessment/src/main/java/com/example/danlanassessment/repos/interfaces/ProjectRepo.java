package com.example.danlanassessment.repos.interfaces;

import com.example.danlanassessment.models.Project;
import com.example.danlanassessment.utils.Response;

import java.util.List;

public interface ProjectRepo {
    Response<List<Project>> getProjectsByEmployeeId(Long employeeId);
}
