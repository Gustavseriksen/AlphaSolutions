package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Project;
import org.example.alphasolutions.models.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Test
    void addProject_Test() {
        Project project = new Project(0, "Test Project", "Desc", LocalDate.of(2025, 5, 1), LocalDate.of(2025, 6, 1), 100, 0, Priority.HIGH, Status.IN_PROGRESS);
        projectRepository.addProject(project);

        List<Project> projects = projectRepository.getAllProjects();
        boolean found = false;
        for (Project p : projects) {
            if (p.getProjectName().equals("Test Project") && p.getProjectDescription().equals("Desc")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    void deleteProject_Test() {
        // Deletes project with id 1
        projectRepository.deleteProject(1);

        List<Project> projects = projectRepository.getAllProjects();
        boolean found = false;
        for (Project p : projects) {
            if (p.getProjectId() == 1) {
                found = true;
                break;
            }
        }
        assertFalse(found);
    }

    @Test
    void getAllProjects_Test() {
        List<Project> projects = projectRepository.getAllProjects();

        assertNotNull(projects);
        assertTrue(projects.size() >= 2); // 2 based on testdata

        // check on projectnames
        boolean containsAlpha = false;
        boolean containsBeta = false;
        for (Project p : projects) {
            if (p.getProjectName().equals("Project Alpha")) containsAlpha = true;
            if (p.getProjectName().equals("Project Beta")) containsBeta = true;
        }
        assertTrue(containsAlpha);
        assertTrue(containsBeta);
    }

    @Test
    void getProjectByProjectId_Test() {
        Project project = projectRepository.getProjectByProjectId(1);

        assertNotNull(project);
        assertEquals("Project Alpha", project.getProjectName());
    }




    @Test
    void getLastInsertedProjectId_Test() {
        Project project = new Project(0, "New Project", "Desc", LocalDate.now(), LocalDate.now().plusDays(10), 10, 0, Priority.MEDIUM, Status.PENDING);
        projectRepository.addProject(project);

        List<Project> projects = projectRepository.getAllProjects();
        int lastId = projects.getLast().getProjectId();
        Project retrieved = projectRepository.getProjectByProjectId(lastId);

        assertEquals("New Project", retrieved.getProjectName());
    }

    @Test
    void updateProject_Test() {
        Project updatedProject = new Project(0, "Updated Project", "Updated Desc", LocalDate.of(2025, 7, 1), LocalDate.of(2025, 8, 1), 50, 20, Priority.LOW, Status.IN_PROGRESS);

        projectRepository.editProject(1, updatedProject);

        Project fromDb = projectRepository.getProjectByProjectId(1);

        assertEquals("Updated Project", fromDb.getProjectName());
        assertEquals("Updated Desc", fromDb.getProjectDescription());
        assertEquals(Priority.LOW, fromDb.getProjectPriority());
        assertEquals(Status.IN_PROGRESS, fromDb.getProjectStatus());
    }


}