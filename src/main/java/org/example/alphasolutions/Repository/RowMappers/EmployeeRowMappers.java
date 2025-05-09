package org.example.alphasolutions.Repository.RowMappers;

import org.example.alphasolutions.Model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMappers implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
                rs.getInt("employee_id"),
                rs.getString("username"),
                rs.getString("password")
        );
    }


}