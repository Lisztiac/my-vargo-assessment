package com.example.danlanassessment.repos;

import com.example.danlanassessment.models.Project;
import com.example.danlanassessment.repos.interfaces.ProjectRepo;
import com.example.danlanassessment.utils.Response;
import com.example.danlanassessment.utils.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepoImp implements ProjectRepo {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Response<List<Project>> getProjectsByEmployeeId(Long employeeId) {
        SqlRowSet rowSet;
        try {
            String sql = "SELECT * FROM danlandb.project p " +
                         "JOIN danlandb.employee_project ep " +
                         "ON ep.project_id = p.id " +
                         "WHERE ep.employee_id = ?";
            rowSet = jdbc.queryForRowSet(sql, employeeId);
        }
        catch (DataAccessException e) {
            ResponseError error = new ResponseError("data access", "an unexpected error has occurred");
            return new Response<>(error, null);
        }

        List<Project> projects = new ArrayList<>();
        while (rowSet.next()) {
            try {
                Project project = mapProject(rowSet);
                projects.add(project);
            }
            catch (InvalidResultSetAccessException e) {
                ResponseError error = new ResponseError("data access", "an unexpected error has occurred");
                return new Response<>(error, null);
            }
        }
        if (projects.size() == 0) {
            ResponseError error = new ResponseError("id", "no projects found");
            return new Response<>(error, null);
        }
        return new Response<>(null, projects);
    }

    private Project mapProject(SqlRowSet rowSet) {
        return new Project(
                rowSet.getLong("id"),
                rowSet.getString("code_name"),
                rowSet.getDate("start_date"),
                rowSet.getDate("end_date")
        );
    }
}
