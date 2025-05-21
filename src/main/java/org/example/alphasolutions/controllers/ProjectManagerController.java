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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/alphaSolutions/pm")
public class ProjectManagerController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final EmployeeProjectsService employeeProjectsService;
    private final SubprojectService subprojectService;
    private final TaskService taskService;


    public ProjectManagerController(ProjectService projectService, EmployeeService employeeService, EmployeeProjectsService employeeProjectsService, SubprojectService subprojectService, TaskService taskService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.employeeProjectsService = employeeProjectsService;
        this.subprojectService = subprojectService;
        this.taskService = taskService;
    }

    @GetMapping("/projectmanager-frontpage")
    public String viewProjectManagerFrontpage(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        //model.addAttribute("session", session.getAttribute("ID"));

        String username = (String) session.getAttribute("username");
        List<Project> projects = projectService.getAllProjects();

        // Tilføj map med ansatte pr. projekt
        Map<Integer, List<Employee>> employeeMap = new HashMap<>();
        for (Project project : projects) {
            employeeMap.put(project.getProjectId(), employeeProjectsService.getEmployeesByProjectId(project.getProjectId()));
        }

        model.addAttribute("username", username);
        model.addAttribute("projects", projects);
        model.addAttribute("employeeMap", employeeMap);

        return "/projectmanager/projectmanager-frontpage";
    }


    @GetMapping("/projectmanager-add-project")
    public String viewAddProject(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        model.addAttribute("project", new Project());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "/projectmanager/projectmanager-add-project";

    }

    @PostMapping("/add-project")
    public String addProject(@ModelAttribute Project project, @RequestParam(value = "selectedEmployees", required = false) List<Integer> selectedEmployeeIds, HttpSession session) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        employeeProjectsService.addProjectWithEmployees(project, selectedEmployeeIds);

        return "redirect:/alphaSolutions/pm/projectmanager-frontpage";
    }


    @GetMapping("/projectmanager-edit-project/{projectId}")
    public String editProjectbyId(@PathVariable int projectId, HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        List<Integer> projectEmployeesIds = new ArrayList<>();
        for (Employee e : employeeProjectsService.getEmployeesByProjectId(projectId)) {
            projectEmployeesIds.add(e.getEmployee_id());
        }


        model.addAttribute("project", projectService.getProjectByProjectId(projectId));
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("projectEmployeesIds", projectEmployeesIds);

        return "/projectmanager/projectmanager-edit-project";

    }

    @PostMapping("/projectmanager-update-project/{projectId}")
    public String updateProjectByID(@PathVariable int projectId,
                                    @ModelAttribute Project project,
                                    @RequestParam(value = "selectedEmployees", required = false) List<Integer> selectedEmployeeIds,
                                    HttpSession session) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        employeeProjectsService.updateProjectWithEmployees(projectId, project, selectedEmployeeIds);
        return "redirect:/alphaSolutions/pm/projectmanager-frontpage";
    }


    @PostMapping("/projectmanager-delete-project/{projectId}")
    public String deleteProject(@PathVariable int projectId, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        projectService.deleteProject(projectId);
        return "redirect:/alphaSolutions/pm/projectmanager-frontpage";

    }

    // SUBPROJECT ----------------------------------------------------------------------------------

    @GetMapping("/projectmanager-project/{projectId}")
    public String viewProject(HttpSession session, @PathVariable int projectId, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        model.addAttribute("project", projectService.getProjectByProjectId(projectId));
        model.addAttribute("subprojects", subprojectService.getSubprojectsByProjectId(projectId));


        return "/projectmanager/projectmanager-project";
    }

    @GetMapping("/projectmanager-add-subproject/{projectId}")
    public String projectmanagerAddSubproject(HttpSession session, Model model, @PathVariable int projectId) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        Subproject subproject = new Subproject();
        subproject.setProjectId(projectId);

        model.addAttribute("subproject", subproject);

        return "/projectmanager/projectmanager-add-subproject";
    }

    @PostMapping("/add-subproject")
    public String addSubproject(HttpSession session, @ModelAttribute Subproject subproject) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        subprojectService.addSubproject(subproject);
        return "redirect:/alphaSolutions/pm/projectmanager-project/" + subproject.getProjectId();
    }

    @PostMapping("/delete-subproject/{subprojectId}/{projectId}")
    public String deleteSubproject(HttpSession session, @PathVariable int subprojectId, @PathVariable int projectId) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        Project project = projectService.getProjectByProjectId(projectId);
        Subproject subproject = subprojectService.getSubprojectBySubId(subprojectId);
        project.setActualHoursUsed(project.getActualHoursUsed() - subproject.getActualHoursUsed());
        projectService.editProject(projectId, project);
        subprojectService.deleteSubproject(subprojectId);
        return "redirect:/alphaSolutions/pm/projectmanager-project/" + projectId;
    }

    @GetMapping("/edit-subproject/{subprojectId}/{projectId}")
    public String editSubproject(HttpSession session, @PathVariable int subprojectId, @PathVariable int projectId, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        model.addAttribute("subprojectId", subprojectId);
        model.addAttribute("projectId", projectId);
        model.addAttribute("subproject", subprojectService.getSubprojectBySubId(subprojectId));
        return "/projectmanager/projectmanager-edit-subproject";
    }

    @PostMapping("/edit-subproject/{subprojectId}/{projectId}")
    public String editSubproject(HttpSession session, @PathVariable int subprojectId, @PathVariable int projectId,
                                 @ModelAttribute Subproject subproject) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        subprojectService.editSubproject(subprojectId, subproject);
        return "redirect:/alphaSolutions/pm/projectmanager-project/" + projectId;
    }


    // TASK ----------------------------------------------------------------------------------

    @GetMapping("/projectmanager-task/{subprojectId}")
    public String getTaskPage(HttpSession session, @PathVariable int subprojectId, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        Subproject subproject = subprojectService.getSubprojectBySubId(subprojectId);

        model.addAttribute("project", projectService.getProjectByProjectId(subproject.getProjectId()));
        model.addAttribute("subproject", subproject);
        model.addAttribute("tasks", taskService.getTasksBySubId(subprojectId));


        return "/projectmanager/projectmanager-task";
    }

    @GetMapping("/projectmanager-add-task/{subprojectId}")
    public String AddTask(HttpSession session, @PathVariable int subprojectId, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        Task task = new Task();
        task.setSubProjectId(subprojectId);


        model.addAttribute("task", task);
        model.addAttribute("subprojectId", subprojectId);

        return "/projectmanager/projectmanager-add-task";
    }

    @PostMapping("/projectmanager-add-task/{subprojectId}")
    public String AddTask(HttpSession session, @PathVariable int subprojectId, @ModelAttribute Task task) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        task.setSubProjectId(subprojectId);
        taskService.addTask(task);

        return "redirect:/alphaSolutions/pm/projectmanager-task/" + subprojectId;
    }

    @PostMapping("/projectmanager-delete-task/{taskId}")
    public String DeleteTask(HttpSession session, @PathVariable int taskId) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        Task task = taskService.getTaskByTaskId(taskId);
        Subproject subproject = subprojectService.getSubprojectBySubId(task.getSubProjectId());
        Project project = projectService.getProjectByProjectId(subproject.getProjectId());

        subproject.setActualHoursUsed(subproject.getActualHoursUsed() - task.getActualUsedHours());
        project.setActualHoursUsed(project.getActualHoursUsed() - task.getActualUsedHours());

        subprojectService.editSubproject(subproject.getSubProjectId(), subproject);
        projectService.editProject(project.getProjectId(), project);
        taskService.deleteTask(taskId);

        return "redirect:/alphaSolutions/pm/projectmanager-task/" + task.getSubProjectId();
    }

    @GetMapping("/projectmanager-edit-task/{taskId}")
    public String projectmanagerEditTask(HttpSession session, @PathVariable int taskId, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        Task task = taskService.getTaskByTaskId(taskId);
        model.addAttribute("task", task);

        return "/projectmanager/projectmanager-edit-task";
    }

    @PostMapping("/projectmanager-edit-task")
    public String projectmanagerEditTask(HttpSession session, @ModelAttribute Task task) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        taskService.editTask(task.getTaskId(), task);

        return "redirect:/alphaSolutions/pm/projectmanager-task/" + task.getSubProjectId();
    }

    @PostMapping("update-actual-hours/{taskId}")
    public String updateActualHours(HttpSession session, @PathVariable int taskId, @RequestParam int number) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        Task task = taskService.getTaskByTaskId(taskId);
        boolean success = taskService.updateActualHours(taskId, number);

        if (!success) {
            return "redirect:/alphaSolutions/pm/projectmanager-task/" + task.getSubProjectId();
        }
        return "redirect:/alphaSolutions/pm/projectmanager-task/" + task.getSubProjectId();

    }


    @GetMapping("/projectmanager-task-info/{taskId}")
    public String viewTaskInfo(HttpSession session, @PathVariable int taskId, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "index";
        }

        model.addAttribute("task", taskService.getTaskByTaskId(taskId));

        return "/projectmanager/projectmanager-task-info";
    }


}
