package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.models.ProjectManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void addEmployeeTest() {
        //Arrange
        Employee newEmployee = new Employee();
        newEmployee.setUsername("EMP_johannes");
        newEmployee.setPassword("PW_johannes");

        //Act
        employeeRepository.addEmployee(newEmployee);
        List<Employee> employees = employeeRepository.getAllEmployees();

        boolean found = false;
        for (Employee e : employees) {
            if (e.getUsername().equals("EMP_johannes") && e.getPassword().equals("PW_johannes")) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    void getAllEmployeesTest() {
        // Act
        List<Employee> employees = employeeRepository.getAllEmployees();

        // Assert
        assertNotNull(employees);
        assertEquals(2, employees.size());

        boolean foundBert = false;
        boolean foundJane = false;

        for (Employee employee : employees) {
            if (employee.getUsername().equals("EMP_bert")) {
                foundBert = true;
            }
            if (employee.getUsername().equals("EMP_jane")) {
                foundJane = true;
            }
        }

        assertTrue(foundBert, "EMP_bert should be in the list");
        assertTrue(foundJane, "EMP_jane should be in the list");
    }

    @Test
    void getEmployeeByIdTest() {
        // Act
        Employee employee = employeeRepository.getEmployeeById(1);

        // Assert
        assertNotNull(employee);
        assertEquals(1, employee.getEmployee_id());
        assertEquals("EMP_bert", employee.getUsername());
        assertEquals("123", employee.getPassword());
    }

    @Test
    void editEmployeeByIdTest() {
        // Arrange
        int employeeId = 1; // EMP_bert has ID 1
        Employee updatedEmployee = new Employee();
        updatedEmployee.setUsername("EMP_bert_updated");
        updatedEmployee.setPassword("new_password");

        // Act
        employeeRepository.editEmployeeById(employeeId, updatedEmployee);

        // Assert
        Employee result = employeeRepository.getEmployeeById(employeeId);

        assertEquals("EMP_bert_updated", result.getUsername());
        assertEquals("new_password", result.getPassword());
    }


    @Test
    void deleteEmployeeTest() {
        // Arrange
        Employee tempEmployee = new Employee();
        tempEmployee.setUsername("EMP_temp");
        tempEmployee.setPassword("temp123");
        employeeRepository.addEmployee(tempEmployee);

        // Henter alle ansatte før sletning
        List<Employee> beforeDelete = employeeRepository.getAllEmployees();
        Employee lastEmployee = beforeDelete.getLast(); // Forvent at det er den vi lige tilføjede

        // Act
        employeeRepository.deleteEmployee(lastEmployee.getEmployee_id());

        // Assert
        List<Employee> afterDelete = employeeRepository.getAllEmployees();
        assertEquals(beforeDelete.size() - 1, afterDelete.size());
    }

}