package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.rowMappers.EmployeeRowMappers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO Employees (username, password) VALUES (?,?)";
        jdbcTemplate.update(sql, employee.getUsername(), employee.getPassword());
    }

    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM Employees";
        // Her genbruger vi rowmappers fra klassen EmployeeRowMappers:
        return jdbcTemplate.query(sql, new EmployeeRowMappers());
    }

    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM Employees WHERE employee_id = ?";

        // Her genbruger vi kode inde fra EmployeeRowMappers-klassen:
        return jdbcTemplate.queryForObject(sql, new Object[]{employeeId}, new EmployeeRowMappers());

    }

    public void editEmployeeById(int employeeId, Employee employee) {
        String sql = "UPDATE Employees SET  " +
                "username = ?, password = ? WHERE employee_id = ?";
        jdbcTemplate.update(sql, employee.getUsername(), employee.getPassword(), employeeId);
    }

    public void deleteEmployee(int employeeId) {
        String sql = "DELETE FROM Employees WHERE employee_id = ?";

        jdbcTemplate.update(sql, employeeId);
    }

}
