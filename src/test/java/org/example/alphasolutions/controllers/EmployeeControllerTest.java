package org.example.alphasolutions.controllers;

import org.example.alphasolutions.models.*;
import org.example.alphasolutions.services.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    ProjectService projectService;
    @MockitoBean
    EmployeeProjectsService employeeProjectsService;
    @MockitoBean
    EmployeeService employeeService;
    @MockitoBean
    SubprojectService subprojectService;
    @MockitoBean
    TaskService taskService;

    // This simulates a user's session (like when you're logged in).
    private MockHttpSession session;

    // Before each test, this code runs. It sets up a new clean session.
    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        session.setAttribute("ID", "123EMP"); // Simulates an employee ID in the session
        session.setAttribute("username", "employeeUser"); // Simulates a username
    }


    @Test
    void whenNotLoggedInAsEmployee_thenRedirectToIndex() throws Exception {
        // Create a session that does NOT have an "EMP" ID
        MockHttpSession invalidSession = new MockHttpSession();
        invalidSession.setAttribute("ID", "123PM"); // This ID looks like a Project Manager

        // Simulate a GET request to the employee frontpage with this invalid session
        mockMvc.perform(get("/alphaSolutions/emp/employee-frontpage").session(invalidSession))
                .andExpect(status().isOk()) // Expect HTTP 200 OK (because it returns the "index" page, not an error)
                .andExpect(view().name("index")); // Expect it to return the "index.html" view
    }

    @Test
    void whenLoggedInWithInvalidEmployeeIDFormat_thenRedirectToIndex() throws Exception {
        // Create a session with an ID that has "EMP" but the number part is wrong
        MockHttpSession invalidSession = new MockHttpSession();
        invalidSession.setAttribute("ID", "ABCEMP"); // "ABC" cannot be converted to a number
        invalidSession.setAttribute("username", "employeeUser");

        // Simulate the request
        mockMvc.perform(get("/alphaSolutions/emp/employee-frontpage").session(invalidSession))
                .andExpect(status().isOk()) // Still 200 OK, because it gracefully returns to index
                .andExpect(view().name("index")); // Expect it to return the "index.html" view
    }

    @Test
    void whenLoggedInAsEmployee_showFrontPage() throws Exception {
        int employeeId = 123; // This matches the "123" from "123EMP" in the session

        // Create some "fake" Project objects that the service would normally return
        Project project1 = new Project();
        project1.setProjectId(1);
        project1.setProjectName("Employee Project Alpha");
        project1.setProjectPriority(Priority.LOW);
        project1.setProjectStatus(Status.IN_PROGRESS); // Set a realistic status using YOUR Status enum
        // You can set other fields here if your Thymeleaf uses them, like budget, deadline, etc.

        Project project2 = new Project();
        project2.setProjectId(2);
        project2.setProjectName("Employee Project Beta");
        project2.setProjectPriority(Priority.MEDIUM);
        project2.setProjectStatus(Status.COMPLETED); // Another realistic status
        // Set other fields as needed

        // Put the fake projects into a list
        List<Project> dummyProjects = Arrays.asList(project1, project2);

        // Tell our "fake" ProjectService what to do:
        // When asked for projects by employee ID 123, return our dummy projects.
        when(projectService.getProjectsByEmployeeId(employeeId)).thenReturn(dummyProjects);
        // When asked for employees by ANY project ID, just return an empty list (for simplicity in this test).
        when(employeeProjectsService.getEmployeesByProjectId(anyInt())).thenReturn(Collections.emptyList());

        // Perform the GET request to the employee frontpage
        mockMvc.perform(get("/alphaSolutions/emp/employee-frontpage").session(session))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(view().name("/employee/employee-frontpage")) // Expect the correct HTML template
                .andExpect(model().attribute("username", "employeeUser")) // Check if 'username' is in the model
                .andExpect(model().attribute("projects", hasSize(2))) // Check if 'projects' list has 2 items
                .andExpect(model().attribute("projects", hasItem(project1))) // Check if project1 is in the list
                .andExpect(model().attribute("projects", hasItem(project2))) // Check if project2 is in the list
                .andExpect(model().attributeExists("employeeMap")); // Check if 'employeeMap' is in the model
    }

    @Test
    void whenLoggedInAsEmployee_showEmployeeTaskPage() throws Exception {
        int subprojectId = 101;
        int projectId = 1;

        // Create dummy objects for subproject, its parent project, and a task
        Subproject dummySubproject = new Subproject();
        dummySubproject.setSubProjectId(subprojectId);
        dummySubproject.setSubProjectName("Employee Subproject Task Test");
        dummySubproject.setProjectId(projectId); // Link to its parent project

        Project dummyProject = new Project();
        dummyProject.setProjectId(projectId);
        dummyProject.setProjectName("Parent Project for Subtask");
        dummyProject.setProjectStatus(Status.NOT_STARTED); // Set a status for the parent project

        Task task1 = new Task();
        task1.setTaskId(201);
        task1.setTaskName("First task in subproject");
        task1.setPriority(Priority.HIGH);
        task1.setStatus(Status.NOT_STARTED);
        task1.setSubProjectId(subprojectId); // Link to its parent subproject

        List<Task> dummyTasks = Collections.singletonList(task1); // A list with one task

        // Tell the services what to return when called
        when(subprojectService.getSubprojectBySubId(subprojectId)).thenReturn(dummySubproject);
        when(projectService.getProjectByProjectId(projectId)).thenReturn(dummyProject);
        when(taskService.getTasksBySubId(subprojectId)).thenReturn(dummyTasks);

        // Perform GET request to the employee task page for a specific subproject
        mockMvc.perform(get("/alphaSolutions/emp/employee-task/{subprojectId}", subprojectId).session(session))
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(view().name("/employee/employee-task")) // Expect the correct view
                .andExpect(model().attribute("subproject", dummySubproject)) // Check subproject in model
                .andExpect(model().attribute("project", dummyProject)) // Check parent project in model
                .andExpect(model().attribute("tasks", hasSize(1))) // Check tasks list size
                .andExpect(model().attribute("tasks", hasItem(task1))); // Check if task1 is in the list
    }

    @Test
    void employeeCanUpdateActualHours_success() throws Exception {
        int taskId = 201;
        int newHours = 5;
        int subprojectId = 101;

        // Create a dummy task (we need its subproject ID for the redirect)
        Task dummyTask = new Task();
        dummyTask.setTaskId(taskId);
        dummyTask.setSubProjectId(subprojectId);

        // Tell services what to return:
        when(taskService.getTaskByTaskId(taskId)).thenReturn(dummyTask);
        when(taskService.updateActualHours(taskId, newHours)).thenReturn(true); // Simulate a successful update

        // Perform a POST request to update hours
        mockMvc.perform(post("/alphaSolutions/emp/update-actual-hours/{taskId}", taskId)
                        .session(session)
                        .param("number", String.valueOf(newHours))) // Pass the 'number' parameter
                .andExpect(status().is3xxRedirection()) // Expect a redirect (HTTP 302)
                .andExpect(redirectedUrl("/alphaSolutions/emp/employee-task/" + subprojectId)); // Check redirect URL

        // Verify that the updateActualHours method was actually called on the service
        verify(taskService).updateActualHours(taskId, newHours);
    }

    @Test
    void employeeCanUpdateActualHours_failure() throws Exception {
        int taskId = 201;
        int newHours = -5; // Example of an input that might cause a failure
        int subprojectId = 101;

        Task dummyTask = new Task();
        dummyTask.setTaskId(taskId);
        dummyTask.setSubProjectId(subprojectId);

        when(taskService.getTaskByTaskId(taskId)).thenReturn(dummyTask);
        when(taskService.updateActualHours(taskId, newHours)).thenReturn(false); // Simulate a failed update

        mockMvc.perform(post("/alphaSolutions/emp/update-actual-hours/{taskId}", taskId)
                        .session(session)
                        .param("number", String.valueOf(newHours)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/alphaSolutions/emp/employee-task/" + subprojectId)); // Still redirects to the same page

        verify(taskService).updateActualHours(taskId, newHours);
    }


    @Test
    void whenLoggedIn_showEmployeeProjectPage() throws Exception {
        int projectId = 1;

        // Create a dummy project and a subproject within it
        Project dummyProject = new Project();
        dummyProject.setProjectId(projectId);
        dummyProject.setProjectName("Employee Detailed Project View");
        dummyProject.setProjectStatus(Status.PENDING); // Set a status

        Subproject subproject1 = new Subproject();
        subproject1.setSubProjectId(101);
        subproject1.setSubProjectName("Subproject for Project View");
        subproject1.setProjectId(projectId); // Link to its parent project

        List<Subproject> dummySubprojects = Collections.singletonList(subproject1);

        // Tell services what to return
        when(projectService.getProjectByProjectId(projectId)).thenReturn(dummyProject);
        when(subprojectService.getSubprojectsByProjectId(projectId)).thenReturn(dummySubprojects);

        // Perform GET request to the employee project page
        mockMvc.perform(get("/alphaSolutions/emp/employee-project/{projectId}", projectId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/employee/employee-project"))
                .andExpect(model().attribute("project", dummyProject)) // Check project in model
                .andExpect(model().attribute("subprojects", hasSize(1))) // Check subprojects list size
                .andExpect(model().attribute("subprojects", hasItem(subproject1))); // Check if subproject1 is in the list
    }


    @Test
    void whenLoggedIn_showTaskInfoPage() throws Exception {
        int taskId = 201;
        // Create a dummy task with some details
        Task dummyTask = new Task();
        dummyTask.setTaskId(taskId);
        dummyTask.setTaskName("Task Info Details Test");
        dummyTask.setEstimatedHours(40);
        dummyTask.setActualUsedHours(20);
        // Add other task fields as needed

        // Tell the service to return this dummy task when asked
        when(taskService.getTaskByTaskId(taskId)).thenReturn(dummyTask);

        // Perform GET request to the task info page
        mockMvc.perform(get("/alphaSolutions/emp/employee-task-info/{taskId}", taskId).session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("/employee/employee-task-info"))
                .andExpect(model().attribute("task", dummyTask)); // Check if the task is in the model
    }
}