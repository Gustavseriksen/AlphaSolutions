package org.example.alphasolutions.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.models.Project;
import org.example.alphasolutions.services.EmployeeProjectsService;
import org.example.alphasolutions.services.EmployeeService;
import org.example.alphasolutions.services.ProjectService;
import org.example.alphasolutions.services.SubprojectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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


    public EmployeeController(EmployeeService employeeService, ProjectService projectService, EmployeeProjectsService employeeProjectsService, SubprojectService subprojectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.employeeProjectsService = employeeProjectsService;
        this.subprojectService = subprojectService;
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



    //public String viewProjectManagerFrontpage(HttpSession session, Model model) {



/*
    // Task
    @PostMapping("/add-task")
    public String addTask(@ModelAttribute Task task) {
        managementSoftwareService.addTask(task);
        return "redirect:/";
    }

    @PostMapping("/delete-task/{taskId}")
    public String deleteTask(@PathVariable int taskId) {
        managementSoftwareService.deleteTask(taskId);
        return "redirect:/";
    }

    @PostMapping("/edit-task")
    public String editTask() {
        managementSoftwareService.editTask();
        return "redirect:/";
    }

//    // Check if done
//    @GetMapping("/check-if-done")
//    public String checkIfDone(Model model) {
//        managementSoftwareService.checkIfDone();
//        return "index";
//    }
//}
*/
}
