package org.example.alphasolutions.controllers;

import org.example.alphasolutions.models.ProjectManager;
import org.example.alphasolutions.services.EmployeeService;
import org.example.alphasolutions.services.ProjectManagerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    ProjectManagerService projectManagerService;

    @MockitoBean
    EmployeeService employeeService;



    @Test
    void whenNotLoggedInAsAdmin_thenRedirectToIndex() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ID", "123"); // ikke ADM

        mockMvc.perform(get("/alphaSolutions/admin/admin-frontpage").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void whenLoggedInAsAdmin_showFrontPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ID", "001ADM");
        session.setAttribute("username", "adminUser");

        mockMvc.perform(get("/alphaSolutions/admin/admin-frontpage").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/admin-frontpage"))
                .andExpect(model().attribute("username", "adminUser"));
    }

    @Test
    void whenAdmin_showProjectManagers() throws Exception {
        // Arrange
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ID", "999ADM");

        ProjectManager pm1 = new ProjectManager();  // Brug dine egne felter her
        pm1.setUsername("PM_Alice");

        ProjectManager pm2 = new ProjectManager();
        pm2.setUsername("PM_Bob");

        List<ProjectManager> dummyPMs = List.of(pm1, pm2);
        when(projectManagerService.getAllProjectManagers()).thenReturn(dummyPMs);

        // Act & Assert
        mockMvc.perform(get("/alphaSolutions/admin/admin-projectmanagers-page").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/admin-projectmanagers-page"))
                .andExpect(model().attribute("projectManagers", hasSize(2)))
                .andExpect(model().attribute("projectManagers", hasItem(pm1)))
                .andExpect(model().attribute("projectManagers", hasItem(pm2)));

    }

    @Test
    void whenAdmin_showEdit() throws Exception {
        // Arrange
        int testId = 42;
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ID", "999ADM");

        ProjectManager dummyPM = new ProjectManager();
        dummyPM.setProjectManagerId(testId);
        dummyPM.setUsername("Alice");

        when(projectManagerService.getProjectManagerById(testId)).thenReturn(dummyPM);

        // Act & Assert
        mockMvc.perform(get("/alphaSolutions/admin/admin-edit-projectmanager/{projectManagerId}", testId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/admin-edit-projectmanager"))
                .andExpect(model().attribute("projectManager", dummyPM))
                .andExpect(model().attribute("projectManagerId", testId));
    }

    @Test
    void adminCanUpdateProjectManager() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ID", "999ADM");

        mockMvc.perform(post("/alphaSolutions/admin/admin-update-projectmanager/1")
                        .session(session)
                        .param("name", "PM_Alice")
                        .param("password", "alice@123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions/admin/admin-projectmanagers-page"));
    }

    @Test
    void adminCanDeleteProjectManager() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ID", "999ADM");

        mockMvc.perform(post("/alphaSolutions/admin/deleteProjectManager/1").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions/admin/admin-projectmanagers-page"));

        verify(projectManagerService).deleteProjectManager(1);
    }

    @Test
    void adminCanViewAddProjectManagerPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ID", "999ADM");

        mockMvc.perform(get("/alphaSolutions/admin/admin-add-projectmanager").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/admin/admin-add-projectmanager"))
                .andExpect(model().attributeExists("projectmanager"));
    }

    @Test
    void adminCanAddProjectManager() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ID", "999ADM");

        ProjectManager pm = new ProjectManager();
        pm.setUsername("JohnDoe");

        mockMvc.perform(post("/alphaSolutions/admin/add-projectmanager")
                        .session(session)
                        .flashAttr("projectManager", pm))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions/admin/admin-projectmanagers-page"));
    }


}