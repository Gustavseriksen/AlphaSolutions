package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Employee;
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
class EmployeeProjectsRepositoryTest {

    @Autowired
    EmployeeProjectsRepository employeeProjectsRepository;


    @Test
    void getEmployeesByProjectIdTest() {
        // Gets already existing relation (EMP_bert is connected to Alpha project)
        List<Employee> employees = employeeProjectsRepository.getEmployeesByProjectId(1); // Project Alpha

        assertEquals(1, employees.size());
        assertEquals("EMP_bert", employees.getFirst().getUsername());
    }


    @Test

    void assignEmployeeToProjectTest() {

        //Assigns EMP_jane (id=2) to Project Beta (id=2)
        employeeProjectsRepository.assignEmployeeToProject(2, 2); // Jane til Beta

        List<Employee> employees = employeeProjectsRepository.getEmployeesByProjectId(2);
        assertEquals(1, employees.size());
        assertEquals("EMP_jane", employees.getFirst().getUsername());
    }


    @Test
    void deleteAllEmployeeAssignmentsFromProjectTest() {
        // Deletes all assignmees for project Alpha (id=1)
        employeeProjectsRepository.deleteAllEmployeeAssignmentsFromProject(1); // Project Alpha

        List<Employee> employees = employeeProjectsRepository.getEmployeesByProjectId(1);
        assertTrue(employees.isEmpty());
    }
}
