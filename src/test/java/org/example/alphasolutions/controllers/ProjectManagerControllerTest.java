package org.example.alphasolutions.controllers;

import org.example.alphasolutions.models.*;
import org.example.alphasolutions.services.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectManagerController.class)
class ProjectManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    ProjectService projectService;

    @MockitoBean
    EmployeeService employeeService;

    @MockitoBean
    EmployeeProjectsService employeeProjectsService;

    @MockitoBean
    SubprojectService subprojectService;

    @MockitoBean
    TaskService taskService;

    private MockHttpSession getValidPMSession() {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("ID", "123PM");
        session.setAttribute("username", "john");
        return session;
    }

    @Test
    void viewFrontpage_validSession_shouldReturnFrontpage() throws Exception {
        MockHttpSession session = getValidPMSession();

        Project project = new Project();
        project.setProjectId(1);
        project.setProjectName("Test Project");
        project.setProjectStatus(Status.IN_PROGRESS);
        project.setProjectPriority(Priority.HIGH);
        List<Project> projectList = List.of(project);

        Mockito.when(projectService.getAllProjects()).thenReturn(projectList);
        Mockito.when(employeeProjectsService.getEmployeesByProjectId(1)).thenReturn(List.of());

        mockMvc.perform(get("/alphaSolutions/pm/projectmanager-frontpage").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/projectmanager/projectmanager-frontpage"))
                .andExpect(model().attribute("projects", projectList));
    }

    @Test
    void viewAddProject_validSession_shouldReturnView() throws Exception {
        MockHttpSession session = getValidPMSession();
        Mockito.when(employeeService.getAllEmployees()).thenReturn(List.of());

        mockMvc.perform(get("/alphaSolutions/pm/projectmanager-add-project").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/projectmanager/projectmanager-add-project"));
    }

    @Test
    void viewProject_validSession_shouldReturnProjectPage() throws Exception {
        MockHttpSession session = getValidPMSession();
        int projectId = 1;

        Mockito.when(projectService.getProjectByProjectId(projectId)).thenReturn(new Project());
        Mockito.when(subprojectService.getSubprojectsByProjectId(projectId)).thenReturn(List.of());

        mockMvc.perform(get("/alphaSolutions/pm/projectmanager-project/{projectId}", projectId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/projectmanager/projectmanager-project"));
    }

    @Test
    void viewAddSubproject_validSession_shouldReturnView() throws Exception {
        MockHttpSession session = getValidPMSession();
        int projectId = 1;

        mockMvc.perform(get("/alphaSolutions/pm/projectmanager-add-subproject/{projectId}", projectId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/projectmanager/projectmanager-add-subproject"));
    }

    @Test
    void viewTaskPage_validSession_shouldReturnTaskPage() throws Exception {
        MockHttpSession session = getValidPMSession();
        int subprojectId = 1;

        Subproject subproject = new Subproject();
        subproject.setProjectId(1);

        Mockito.when(subprojectService.getSubprojectBySubId(subprojectId)).thenReturn(subproject);
        Mockito.when(projectService.getProjectByProjectId(1)).thenReturn(new Project());
        Mockito.when(taskService.getTasksBySubId(subprojectId)).thenReturn(List.of());

        mockMvc.perform(get("/alphaSolutions/pm/projectmanager-task/{subprojectId}", subprojectId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/projectmanager/projectmanager-task"));
    }

    @Test
    void viewTaskInfo_validSession_shouldReturnTaskInfoPage() throws Exception {
        MockHttpSession session = getValidPMSession();
        int taskId = 1;

        Mockito.when(taskService.getTaskByTaskId(taskId)).thenReturn(new Task());

        mockMvc.perform(get("/alphaSolutions/pm/projectmanager-task-info/{taskId}", taskId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/projectmanager/projectmanager-task-info"));
    }

    @Test
    void viewEditSubproject_validSession_shouldReturnEditPage() throws Exception {
        MockHttpSession session = getValidPMSession();
        int subprojectId = 1;
        int projectId = 1;

        Mockito.when(subprojectService.getSubprojectBySubId(subprojectId)).thenReturn(new Subproject());

        mockMvc.perform(get("/alphaSolutions/pm/edit-subproject/{subprojectId}/{projectId}", subprojectId, projectId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/projectmanager/projectmanager-edit-subproject"));
    }

    @Test
    void viewEditTask_validSession_shouldReturnEditTaskPage() throws Exception {
        MockHttpSession session = getValidPMSession();
        int taskId = 1;

        Mockito.when(taskService.getTaskByTaskId(taskId)).thenReturn(new Task());

        mockMvc.perform(get("/alphaSolutions/pm/projectmanager-edit-task/{taskId}", taskId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/projectmanager/projectmanager-edit-task"));
    }
}
