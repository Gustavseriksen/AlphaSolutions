package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Admin;
import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.models.ProjectManager;
import org.example.alphasolutions.rowMappers.AdminRowMappers;
import org.example.alphasolutions.rowMappers.EmployeeRowMappers;
import org.example.alphasolutions.rowMappers.ProjectManagerRowMappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CredentialsRepository {

    private JdbcTemplate jdbcTemplate;

    public CredentialsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Admin Credentials
    public Admin checkAdminCredentials(String username, String password) {
        String sql = "SELECT * FROM Admins WHERE username = ? AND password = ?";

        try { // RowMapper der bliver brugt under AdminRowMappers:
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new AdminRowMappers());

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Projectmanager Credentials
    public ProjectManager checkProjectManagerCredentials(String username, String password) {
        String sql = "SELECT * FROM ProjectManagers WHERE username = ? AND password = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new ProjectManagerRowMappers());


        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Employee Credentials
    public Employee checkEmployeeCredentials(String username, String password) {
        String sql = "SELECT * FROM Employees WHERE username = ? AND password = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new EmployeeRowMappers());

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
