package com.example.danlanassessment.services;

import com.example.danlanassessment.models.Project;
import com.example.danlanassessment.repos.interfaces.ProjectRepo;
import com.example.danlanassessment.services.interfaces.ProjectService;
import com.example.danlanassessment.utils.Response;
import com.example.danlanassessment.utils.ResponseError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProjectServiceImpTest {

    private final ProjectRepo projectRepo = Mockito.mock(ProjectRepo.class);
    private ProjectService projectService;

    @BeforeEach
    void setup() {
        projectService = new ProjectServiceImp(projectRepo);
    }

    @Test
    void getProjectByEmployeeId_NullId_ReturnsError() {
        // Arrange
        String expected = "employee id required";

        // Act
        Response<List<Project>> response = projectService.getProjectsByEmployeeId(null);

        // Assert
        ResponseError error = response.getError();

        assertThat(response.getResult()).isNull();
        assertThat(error).isNotNull();
        assertThat(error.getMessage()).isEqualTo(expected);
    }

    @Test
    void getProjectByEmployeeId_ValidId_ReturnsProjects() {
        // Arrange
        var projects = getProjectList();
        var expected = new Response<>(null, projects);
        when(projectRepo.getProjectsByEmployeeId(any(Long.class))).thenReturn(expected);

        // Act
        Response<List<Project>> response = projectService.getProjectsByEmployeeId(1L);

        // Assert
        var retrievedProjects = response.getResult();

        assertThat(response.getError()).isNull();
        assertThat(response.getResult()).isNotNull();
        assertThat(projects).isEqualTo(retrievedProjects).usingRecursiveComparison();
    }

    private List<Project> getProjectList() {

        var project1 = new Project(1L, "tiger king", new Date(), new Date());
        var project2 = new Project(2L, "avatar", new Date(), new Date());
        var project3 = new Project(3L, "superman", new Date(), new Date());
        var project4 = new Project(4L, "batman", new Date(), new Date());

        return Arrays.asList(project1, project2, project3, project4);
    }
}
