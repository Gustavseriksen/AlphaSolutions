package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.repositories.RowMappers.EmployeeRowMappers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeProjectsRepository {

    private JdbcTemplate jdbcTemplate;

    public EmployeeProjectsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void assignEmployeeToProject(int employeeId, int projectId) {
        String sql = "INSERT INTO EmployeeProjects (employee_id, project_id) VALUES(?,?)";
        jdbcTemplate.update(sql, employeeId, projectId);
    }

    public List<Employee> getEmployeesByProjectId(int projectId) {
        String sql = "SELECT e.* FROM Employees e " +
                "JOIN EmployeeProjects ep ON e.employee_id = ep.employee_id " +
                "WHERE ep.project_id = ?";
        return jdbcTemplate.query(sql, new EmployeeRowMappers(), projectId);
    }


    public void deleteAllEmployeeAssignmentsFromProject(int projectId) {
        String sql = "DELETE FROM EmployeeProjects WHERE project_id = ?";
        jdbcTemplate.update(sql, projectId);
    }
}
