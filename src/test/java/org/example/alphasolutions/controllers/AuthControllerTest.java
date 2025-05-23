package org.example.alphasolutions.controllers;

import org.example.alphasolutions.models.Admin;
import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.models.ProjectManager;
import org.example.alphasolutions.services.CredentialsService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    CredentialsService credentialsService;


    @Test
    void viewNonExistentPage() throws Exception {
        mockMvc.perform(get("/nonexistent"))
                .andExpect(status().isNotFound());
    }

    @Test
    void checkCredentials_projectManager() throws Exception {
        String username = "PM_john";
        String password = "123";

        ProjectManager mockPM = new ProjectManager();
        mockPM.setProjectManagerId(2);
        mockPM.setUsername(username);
        mockPM.setPassword(password);

        Mockito.when(credentialsService.checkProjectManagerCredentials(username, password))
                .thenReturn(mockPM);

        mockMvc.perform(post("/alphaSolutions/auth/checkCredentials")
                        .param("username", username)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions/pm/projectmanager-frontpage"));
    }

    @Test
    void checkCredentials_admin() throws Exception {
        String username = "ADM_sara";
        String password = "adminpass";

        Admin mockAdmin = new Admin();
        mockAdmin.setAdmin_id(1);
        mockAdmin.setName(username);
        mockAdmin.setPassword(password);

        Mockito.when(credentialsService.checkAdminCredentials(username, password))
                .thenReturn(mockAdmin);

        mockMvc.perform(post("/alphaSolutions/auth/checkCredentials")
                        .param("username", username)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions/admin/admin-frontpage"));
    }

    @Test
    void checkCredentials_employee() throws Exception {
        String username = "EMP_jane";
        String password = "emppass";

        Employee mockEmployee = new Employee();
        mockEmployee.setEmployee_id(5);
        mockEmployee.setUsername(username);
        mockEmployee.setPassword(password);

        Mockito.when(credentialsService.checkEmployeeCredentials(username, password))
                .thenReturn(mockEmployee);

        mockMvc.perform(post("/alphaSolutions/auth/checkCredentials")
                        .param("username", username)
                        .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions/emp/employee-frontpage"));
    }


    @Test
    void checkCredentials_invalidCredentials() throws Exception {
        String username = "PEM_wrong";
        String password = "wrongpass";


        mockMvc.perform(post("/alphaSolutions/auth/checkCredentials")
                        .param("username", username)
                        .param("password", password))
                .andExpect(view().name("index"));
    }


    @Test
    void testLogoutInvalidatesSessionAndRedirectsToIndex() throws Exception {
        // Arrange: Make Session with data
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "testuser");

        // Act + Assert
        mockMvc.perform(post("/alphaSolutions/auth/logout").session(session))
                .andExpect(status().isOk()) // Hvis du returnerer en template
                .andExpect(view().name("index"));

        // after logout the session should be invalidated
        assertThat(session.isInvalid()).isTrue();
    }
}