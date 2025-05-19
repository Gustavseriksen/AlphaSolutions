package org.example.alphasolutions.repositories;

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
class ProjectManagerRepositoryTest {


    @Autowired
    ProjectManagerRepository projectManagerRepository;


    @Test
    void addProjectManager() {
        // Arrange
        ProjectManager newManager = new ProjectManager();
        newManager.setUsername("PM_jane");
        newManager.setPassword("pw_jane");

        // Act
        projectManagerRepository.addProjectManager(newManager);
        List<ProjectManager> managers = projectManagerRepository.getAllProjectManagers();

        // Assert
        boolean found = false;
        for (ProjectManager m : managers) {
            if (m.getUsername().equals("PM_jane") && m.getPassword().equals("pw_jane")) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    void deleteProjectManager() {
        // Arrange
        ProjectManager manager = new ProjectManager();
        manager.setUsername("PM_temp");
        manager.setPassword("temp123");
        projectManagerRepository.addProjectManager(manager);

        // Finds the newest projectmanager
        List<ProjectManager> beforeDelete = projectManagerRepository.getAllProjectManagers();
        ProjectManager lastManager = beforeDelete.getLast();

        // Act
        projectManagerRepository.deleteProjectManager(lastManager.getProjectManagerId());

        // Assert
        List<ProjectManager> afterDelete = projectManagerRepository.getAllProjectManagers();
        assertEquals(beforeDelete.size() - 1, afterDelete.size());
    }


    @Test
    void getAllProjectManagers_shouldReturnAllProjectManagersFromDatabase() {
        // Act
        List<ProjectManager> managers = projectManagerRepository.getAllProjectManagers();

        // Assert
        assertNotNull(managers);
        assertEquals(2, managers.size());

        boolean foundBert = false;
        boolean foundJohn = false;

        for (ProjectManager manager : managers) {
            if (manager.getUsername().equals("PM_bert")) {
                foundBert = true;
            }
            if (manager.getUsername().equals("PM_john")) {
                foundJohn = true;
            }
        }

        assertTrue(foundBert, "PM_bert should be in the list");
        assertTrue(foundJohn, "PM_john should be in the list");
    }


    @Test
    void getProjectManagerById_shouldReturnCorrectProjectManager() {
        // Act
        ProjectManager manager = projectManagerRepository.getProjectManagerById(1);

        // Assert
        assertNotNull(manager);
        assertEquals(1, manager.getProjectManagerId());
        assertEquals("PM_bert", manager.getUsername());
        assertEquals("123", manager.getPassword());
    }

    @Test
    void editProjectManagerById_shouldUpdateUsernameAndPassword() {
        // Arrange
        int managerId = 1; // PM_bert har ID 1 ifølge testdata
        ProjectManager updatedManager = new ProjectManager();
        updatedManager.setUsername("PM_bert_updated");
        updatedManager.setPassword("new_password");

        // Act
        projectManagerRepository.editProjectManagerById(managerId, updatedManager);

        // Assert
        ProjectManager result = projectManagerRepository.getProjectManagerById(managerId);

        assertEquals("PM_bert_updated", result.getUsername());
        assertEquals("new_password", result.getPassword());
    }

}