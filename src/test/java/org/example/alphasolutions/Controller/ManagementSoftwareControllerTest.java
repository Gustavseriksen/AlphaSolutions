package org.example.alphasolutions.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.alphasolutions.Model.ProjectManager;
import org.example.alphasolutions.Service.ManagementSoftwareService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ManagementSoftwareController.class)
class ManagementSoftwareControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ManagementSoftwareService managementSoftwareService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void viewNonExistingPage() throws Exception {
        mockMvc.perform(get("/nonexisting"))
                .andExpect(status().isNotFound());
    }

    @Test
    void viewFrontPage() throws Exception {
        mockMvc.perform(get("/alphaSolutions")).andExpect(status().isOk());
    }

    @Test
    void logout() throws Exception {
        ManagementSoftwareController controller = new ManagementSoftwareController(managementSoftwareService);
        HttpSession session = mock(HttpSession.class);

        String result = controller.logout(session);

        verify(session).invalidate();
        assertEquals("redirect:/alphaSolutions", result);

    }

    @Test
    void checkCredentials() {
    }

    @Test
    void viewAdminFrontPage_withSession_returnsOk() throws Exception {
        mockMvc.perform(get("/alphaSolutions/admin-frontpage")
                        .sessionAttr("ID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-frontpage"));
    }

    @Test
    void viewAdminFrontPage_withoutSession_redirects() throws Exception {
        mockMvc.perform(get("/alphaSolutions/admin-frontpage"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions"));
    }

    @Test
    void redirect_To_Index_When_NoValidSession() throws Exception {
        mockMvc.perform(get("/admin-projectmanagers-page"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions"));
    }

    @Test
    void viewProjectManagersPage_Admin_SessionIsOK() throws Exception {
        List<ProjectManager> dummyManagers = List.of(new ProjectManager(1, "PM_Alice", "he1"), new ProjectManager(2, "PM_Bob", "john"));
        when(managementSoftwareService.getAllProjectManagers()).thenReturn(dummyManagers);

        mockMvc.perform(get("/alphaSolutions/admin-projectmanagers-page")
                        .sessionAttr("ID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-projectmanagers-page"))
                .andExpect(model().attributeExists("projectManagers"))
                .andExpect(model().attribute("projectManagers", dummyManagers));
    }

    @Test
    void viewAdminEditProjectManager_withAdminSession_returnsOkAndModel() throws Exception {
        // Arrange
        int projectManagerId = 5;
        ProjectManager dummyManager = new ProjectManager(projectManagerId, "PM_Test", "testpass");

        //makes sure that it does not use the real service
        when(managementSoftwareService.getProjectManagerById(projectManagerId)).thenReturn(dummyManager);

        // Act & Assert
        mockMvc.perform(get("/alphaSolutions/admin-edit-projectmanager/{projectManagerId}", projectManagerId)
                        .sessionAttr("ID", "1_ADM")) // Simulerer en admin session
                .andExpect(status().isOk())
                .andExpect(view().name("admin-edit-projectmanager"))
                .andExpect(model().attributeExists("projectManager"))
                .andExpect(model().attribute("projectManager", dummyManager))
                .andExpect(model().attributeExists("projectManagerId"))
                .andExpect(model().attribute("projectManagerId", projectManagerId));

        // Verify service interaction
        verify(managementSoftwareService).getProjectManagerById(projectManagerId);
    }


    @Test
    void adminUpdateProjectManager_withAdminSession_callsServiceAndRedirects() throws Exception {
        // Arrange
        int projectManagerId = 5;
        ProjectManager updatedManager = new ProjectManager(projectManagerId, "UpdatedPM", "newpass");

        // Act & Assert
        mockMvc.perform(post("/alphaSolutions/admin-update-projectmanager/{projectManagerId}", projectManagerId)
                        .sessionAttr("ID", "1_ADM")
                        .flashAttr("projectManager", updatedManager)) // Simulates form-data via flashAttr
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions/admin-projectmanagers-page"));

        // Verify service interaction
        verify(managementSoftwareService).editProjectManagerById(projectManagerId, updatedManager);
    }

    @Test
    void viewAdminEmployeesPage() {
    }

    @Test
    void deleteProjectManager() {
    }

    @Test
    void viewAddProjectManager() {
    }

    @Test
    void addProjectManager() {
    }

    @Test
    void addEmployee() {
    }

    @Test
    void addProject() {
    }

    @Test
    void deleteProject() {
    }

    @Test
    void editProject() {
    }

    @Test
    void addSubproject() {
    }

    @Test
    void deleteSubproject() {
    }

    @Test
    void editSubproject() {
    }

    @Test
    void addTask() {
    }

    @Test
    void deleteTask() {
    }
}