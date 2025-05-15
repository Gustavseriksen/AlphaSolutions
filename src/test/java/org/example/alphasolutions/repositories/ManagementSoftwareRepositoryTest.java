package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
class ManagementSoftwareRepositoryTest {

    @Autowired
    ManagementSoftwareRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void clearEmployeeProjects() {
        jdbcTemplate.update("DELETE FROM EmployeeProjects");
    }


    @Test
    void testAddProjectManager() {
        ProjectManager pm = new ProjectManager();
        pm.setUsername("PM_johan");
        pm.setPassword("JegerSej1");
        repository.addProjectManager(pm);
        ProjectManager result = repository.checkProjectManagerCredentials("PM_johan", "JegerSej1");
        assertNotNull(result);
        assertEquals(2, result.getProjectManagerId());
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        employee.setUsername("EMP_johan");
        employee.setPassword("JegerSej1");

        repository.addEmployee(employee);

        Employee result = repository.checkEmployeeCredentials("EMP_johan", "JegerSej1");

        assertNotNull(result);
        assertEquals(2, result.getEmployee_id()); // Assuming 5 employees were inserted in the initial setup
    }

    @Test
    void testDeleteProjectManager() {
        // First verify the test PM exists (from your h2init.sql)
        ProjectManager pm = repository.checkProjectManagerCredentials("PM_bert", "123");
        assertNotNull(pm, "Project manager should exist before deletion");

        // Deletes the project manager
        repository.deleteProjectManager(pm.getProjectManagerId());

        // Verifies deletion by trying to find the deleted PM / makes sure that the deleted pm is null by checking credentials
        ProjectManager deletedPm = repository.checkProjectManagerCredentials("PM_bert", "123");
        assertNull(deletedPm, "Project manager should be deleted");
    }

    @Test
    void testGetAllProjectManagers() {
        List<ProjectManager> managers = repository.getAllProjectManagers();
        assertNotNull(managers);
        assertFalse(managers.isEmpty()); // At least the test PM exists
        assertEquals(1, managers.size());
    }

    @Test
    void testGetProjectManagerById() {
        // First get the ID of the test PM inserted by h2init.sql
        ProjectManager testPm = repository.checkProjectManagerCredentials("PM_bert", "123");
        assertNotNull(testPm, "Test project manager should exist");

        // Test the method
        ProjectManager result = repository.getProjectManagerById(testPm.getProjectManagerId());

        // Verify the result
        assertNotNull(result, "Should return project manager when ID exists");
        assertEquals("PM_bert", result.getUsername());
        assertEquals("123", result.getPassword());
    }

    @Test
    void testEditProjectManagerById() {
        // 1. Get the existing test PM
        ProjectManager originalPm = repository.checkProjectManagerCredentials("PM_bert", "123");
        assertNotNull(originalPm, "Test project manager should exist");

        // 2. Create updated PM data
        ProjectManager updatedPm = new ProjectManager();
        updatedPm.setUsername("PM_bert_updated");
        updatedPm.setPassword("newpassword123");

        // 3. Execute the edit
        repository.editProjectManagerById(originalPm.getProjectManagerId(), updatedPm);

        // 4. Verify the update
        ProjectManager result = repository.getProjectManagerById(originalPm.getProjectManagerId());
        assertNotNull(result, "Project manager should still exist after update");
        assertEquals("PM_bert_updated", result.getUsername());
        assertEquals("newpassword123", result.getPassword());

        // 5. Verify other fields remain unchanged (if applicable)
        assertEquals(originalPm.getProjectManagerId(), result.getProjectManagerId());
    }


    @Test
    void testAddProject() {
        // Create a new project

        Project newProject = new Project();
        newProject.setProjectName("New Test Project");
        newProject.setProjectDescription("Test description");
        newProject.setProjectStartDate(LocalDate.of(2023, 1, 1));
        newProject.setProjectEndDate(LocalDate.of(2023, 12, 31));
        newProject.setEstimatedHours(100);
        newProject.setActualHoursUsed(0);
        newProject.setProjectPriority(Priority.MEDIUM); // Assuming enum
        newProject.setProjectStatus(Status.NOT_STARTED); // Assuming enum

        // Add the project
        repository.addProject(newProject);

        // Verify the project was added by checking the count
        List<Project> allProjects = repository.getAllProjects();
        assertTrue(allProjects.size() > 1); // More than initial project

        // Alternatively, search for the added project
        boolean projectFound = allProjects.stream()
                .anyMatch(p -> p.getProjectName().equals("New Test Project") &&
                        p.getProjectDescription().equals("Test description"));
        assertTrue(projectFound, "New project should exist in database");
    }

    @Test
    void testDeleteProject() {

        //Check that the project exists before deletion (by retrieving it)
        Project projectForDelete = repository.getProjectByProjectId(1);
        assertNotNull(projectForDelete, "Should exist");

        //deletes project
        repository.deleteProject(1);

        //Makes sure that it is deleted by getting the list of all projects and expecting the size to be 0
        assertEquals(repository.getAllProjects().size(), 0);
    }

    @Test
    void testGetAllProjects() {
        //Gets all projects
        List<Project> projects = repository.getAllProjects();

        // Checks that the list is not null and not empty
        assertNotNull(projects, "Should NOT be null");
        assertFalse(projects.isEmpty(), "Should NOT be empty");

        // Checks the number of projects based on the test database
        assertEquals(1, projects.size(), "The expected value does not match the actual");
    }
    @Test
    void testGetProjectByProjectId() {
        // Positive test for getProjectByProjectId
        Project existingProject = repository.getProjectByProjectId(1); //1 project is inserted
        assertNotNull(existingProject, "Project with id 1 should exist");
    }

    @Test
    void testAssignEmployeeToProjectSimpleAssert() {
        // Defines existing employye and project id
        int existingEmployeeId = 1;
        int existingProjectId = 1;

        // Assigns the employees to the project by id
        repository.assignEmployeeToProject(existingProjectId, existingEmployeeId);

        // Gets the list of assigned employees
        List<Employee> employees = repository.getEmployeesByProjectId(existingProjectId);

        // Verifies that the list is not empty
        assertFalse(employees.isEmpty(), "Should be at least 1 employee assigned");
    }


}