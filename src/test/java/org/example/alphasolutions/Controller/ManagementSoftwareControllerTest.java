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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        Mockito.when(managementSoftwareService.getAllProjectManagers()).thenReturn(dummyManagers);

        mockMvc.perform(get("/alphaSolutions/admin-projectmanagers-page")
                        .sessionAttr("ID", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-projectmanagers-page"))
                .andExpect(model().attributeExists("projectManagers"))
                .andExpect(model().attribute("projectManagers", dummyManagers));
    }

    @Test
    void adminEditProjectManager() {

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