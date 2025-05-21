package org.example.alphasolutions.rowMappers;

import org.example.alphasolutions.models.Employee;
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