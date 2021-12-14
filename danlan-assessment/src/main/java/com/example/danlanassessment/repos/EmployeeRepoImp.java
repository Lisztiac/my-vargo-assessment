package com.example.danlanassessment.repos;

import com.example.danlanassessment.models.Employee;
import com.example.danlanassessment.repos.interfaces.EmployeeRepo;
import com.example.danlanassessment.utils.Response;
import com.example.danlanassessment.utils.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeRepoImp implements EmployeeRepo {
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Response<Employee> getEmployeeById(Long employeeId) {
        SqlRowSet rowSet;
        try {
            String sql = "SELECT * FROM employee e " +
                         "WHERE e.id = ?";
            rowSet = jdbc.queryForRowSet(sql, employeeId);
        }
        catch (DataAccessException e) {
            ResponseError error = new ResponseError("data access", "an unexpected error has occurred");
            return new Response<>(error, null);
        }
        if (rowSet.next()) {
            try {
                Employee employee = mapEmployee(rowSet);
                return new Response<>(null, employee);
            }
            catch (InvalidResultSetAccessException e) {
                ResponseError error = new ResponseError("data access", "an unexpected error has occurred");
                return new Response<>(error, null);
            }
        }
        ResponseError error = new ResponseError("id", "employee not found");
        return new Response<>(error, null);
    }

    private Employee mapEmployee(SqlRowSet rowSet) {
        return new Employee(
                rowSet.getLong("id"),
                rowSet.getString("first_name"),
                rowSet.getString("last_name"),
                rowSet.getString("title"),
                rowSet.getString("company"),
                null
        );
    }
}
