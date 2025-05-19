package org.example.alphasolutions.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.models.Project;
import org.example.alphasolutions.models.Subproject;
import org.example.alphasolutions.models.Task;
import org.example.alphasolutions.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alphaSolutions/emp")
public class EmployeeController {

    private final ProjectService projectService;
    private final EmployeeProjectsService employeeProjectsService;
    private final EmployeeService employeeService;
    private final SubprojectService subprojectService;
    private final TaskService taskService;

    public EmployeeController(EmployeeService employeeService, SubprojectService subprojectService, ProjectService projectService, TaskService taskService, EmployeeProjectsService employeeProjectsService) {
        this.employeeService = employeeService;
        this.subprojectService = subprojectService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.employeeProjectsService = employeeProjectsService;
    }


    @GetMapping("/employee-frontpage")
    public String viewEmployeeFrontpage(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("EMP")) {
            return "index";
        }

        int employeeId;
        try {
            String numericPartOfId = ID.replace("EMP", "");
            employeeId = Integer.parseInt(numericPartOfId);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing employee ID from session: " + ID);
            return "index";
        }


        String username = (String) session.getAttribute("username");
        List<Project> projects = projectService.getProjectsByEmployeeId(employeeId);


        // Tilføj map med ansatte pr. projekt
        Map<Integer, List<Employee>> employeeMap = new HashMap<>();
        for (Project project : projects) {
            employeeMap.put(project.getProjectId(), employeeProjectsService.getEmployeesByProjectId(project.getProjectId()));
        }

        model.addAttribute("username", username);
        model.addAttribute("projects", projects);
        model.addAttribute("employeeMap", employeeMap);

        return "/employee/employee-frontpage";
    }

    @GetMapping("/employee-task/{subprojectId}")
    public String employeeTask(HttpSession session, @PathVariable int subprojectId, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("EMP")) {
            return "index";
        }

        Subproject subproject = subprojectService.getSubprojectBySubId(subprojectId);
        model.addAttribute("subproject", subproject);
        model.addAttribute("project", projectService.getProjectByProjectId(subproject.getProjectId()));
        model.addAttribute("tasks", taskService.getTasksBySubId(subprojectId));


        return "/employee/employee-task";
    }

    @PostMapping("update-actual-hours/{taskId}")
    public String updateActualHours(HttpSession session, @PathVariable int taskId, @RequestParam int number) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("EMP")) {
            return "index";
        }

        Task task = taskService.getTaskByTaskId(taskId);
        boolean success = taskService.updateActualHours(taskId, number);

        if (!success) {
            return "redirect:/alphaSolutions/emp/employee-task/" + task.getSubProjectId();
        }
        return "redirect:/alphaSolutions/emp/employee-task/" + task.getSubProjectId();
    }

    @GetMapping("/employee-project/{projectId}")
    public String viewProject(HttpSession session, @PathVariable int projectId, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("EMP")) {
            return "index";
        }

        model.addAttribute("project", projectService.getProjectByProjectId(projectId));
        model.addAttribute("subprojects", subprojectService.getSubprojectsByProjectId(projectId));


        return "/employee/employee-project";
    }
}
