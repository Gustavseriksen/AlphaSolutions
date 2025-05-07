package org.example.alphasolutions.Repository;

import org.example.alphasolutions.Model.Employee;
import org.example.alphasolutions.Model.ProjectManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
class ManagementSoftwareRepositoryTest {

    @Autowired
    ManagementSoftwareRepository repository;


    @Test
    void testAddProjectManager() {
        ProjectManager pm = new ProjectManager();
        pm.setUsername("PM_johan");
        pm.setPassword("JegerSej1");
        repository.addProjectManager(pm);
        ProjectManager result = repository.checkProjectManagerCredentials("PM_johan", "JegerSej1");
        assertNotNull(result);
        assertEquals(4, result.getProjectManagerId());
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        employee.setUsername("EMP_johan");
        employee.setPassword("JegerSej1");

        repository.addEmployee(employee);

        Employee result = repository.checkEmployeeCredentials("EMP_johan", "JegerSej1");

        assertNotNull(result);
        assertEquals(4, result.getEmployee_id()); // Assuming 5 employees were inserted in the initial setup
    }

}