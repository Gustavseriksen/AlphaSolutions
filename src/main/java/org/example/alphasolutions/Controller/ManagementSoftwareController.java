package org.example.alphasolutions.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.alphasolutions.Model.*;
import org.example.alphasolutions.Service.ManagementSoftwareService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/alphaSolutions")
public class ManagementSoftwareController {
    private final ManagementSoftwareService managementSoftwareService;

    public ManagementSoftwareController(ManagementSoftwareService managementSoftwareService) {
        this.managementSoftwareService = managementSoftwareService;
    }

    // Front Page View
    @GetMapping("")
    public String viewFrontPage() {
        return "index";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // sletter hele sessionen
        return "redirect:/alphaSolutions"; // tilbage til login eller forside
    }

    @GetMapping("/error-duplicate-username-employee")
    public String getErrorDuplicateUsernameEmployee() {
        return "error-duplicate-username-employee";
    }

    @GetMapping("/error-duplicate-username-projectmanager")
    public String getErrorDuplicateUsernameProjectmanager() {
        return "error-duplicate-username-projectmanager";
    }

    // Admin -----------------------------------------------------------------------------

    // CHECK CREDENTIALS -----------------------------------------------------------------------------

    @PostMapping("/checkCredentials")
    public String checkCredentials(@RequestParam String username,
                                   @RequestParam String password,
                                   HttpSession session) {

        if (username.startsWith("ADM")) {
            Admin admin = managementSoftwareService.checkAdminCredentials(username, password);
            if (admin != null) {
                session.setAttribute("ID", admin.getAdmin_id() + "ADM");
                session.setAttribute("username", admin.getUsername());
                return "redirect:/alphaSolutions/admin-frontpage";
            } else {
                return "redirect:/alphaSolutions"; // eller /index hvis det er din login-side
            }

        } else if (username.startsWith("PM")) {
            ProjectManager projectManager = managementSoftwareService.checkProjectManagerCredentials(username, password);
            if (projectManager != null) {
                session.setAttribute("ID", projectManager.getProjectManagerId() + "PM");
                session.setAttribute("username", projectManager.getUsername());
                return "redirect:/alphaSolutions/projectmanager-frontpage";
            } else {
                return "redirect:/alphaSolutions"; // eller /index hvis det er din login-side
            }

        } else if (username.startsWith("EMP")) {
            Employee employee = managementSoftwareService.checkEmployeeCredentials(username, password);
            if (employee != null) {
                session.setAttribute("ID", employee.getEmployee_id() + "EMP");
                session.setAttribute("username", employee.getUsername());
                return "redirect:/alphaSolutions/employee-frontpage";
            } else {
                return "redirect:/alphaSolutions"; // eller /index hvis det er din login-side
            }
        } else {
            return "redirect:/alphaSolutions";
        }
    }

    // ADMIN FRONTPAGE -----------------------------------------------------------------------------

    @GetMapping("/admin-frontpage")
    public String viewAdminFrontPage(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }
        String username = (String) session.getAttribute("username");

        model.addAttribute("username", username);
        return "admin-frontpage";
    }

    // ADMIN PROJECT MANAGERS ENDPOINTS/METHODS -----------------------------------------------------------------------------

    @GetMapping("/admin-projectmanagers-page")
    public String viewAdminProjectManagersPage(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("projectManagers", managementSoftwareService.getAllProjectManagers());

        return "admin-projectmanagers-page";
    }

    @GetMapping("/admin-edit-projectmanager/{projectManagerId}")
    public String adminEditProjectManager(@PathVariable int projectManagerId, HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("projectManager", managementSoftwareService.getProjectManagerById(projectManagerId));
        model.addAttribute("projectManagerId", projectManagerId);

        return "admin-edit-projectmanager";
    }

    @PostMapping("/admin-update-projectmanager/{projectManagerId}")
    public String adminUpdateProjectManager(@PathVariable int projectManagerId, @ModelAttribute ProjectManager projectManager, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.editProjectManagerById(projectManagerId, projectManager);
        return "redirect:/alphaSolutions/admin-projectmanagers-page";
    }

    @PostMapping("/deleteProjectManager/{projectManagerId}")
    public String deleteProjectManager(@PathVariable int projectManagerId, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.deleteProjectManager(projectManagerId);
        return "redirect:/alphaSolutions/admin-projectmanagers-page";
    }

    @GetMapping("/admin-add-projectmanager")
    public String viewAddProjectManager(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("projectmanager", new ProjectManager());

        return "admin-add-projectmanager";
    }

    @PostMapping("/add-projectmanager")
    public String addProjectManager(@ModelAttribute ProjectManager projectManager, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        try {

            String originalUsername = projectManager.getUsername();

            if (!originalUsername.startsWith("PM_")) {

                projectManager.setUsername("PM_" + originalUsername);

            }
            managementSoftwareService.addProjectManager(projectManager);
        } catch (Exception e) {
            return "redirect:/alphaSolutions/error-duplicate-username-projectmanager";
        }

        return "redirect:/alphaSolutions/admin-projectmanagers-page"; // redirect to frontpage for now
    }

    // ADMIN EMPLOYEE ENDPOINTS/METHODS ---------------------------------------------------------------------

    @GetMapping("/admin-edit-employee/{employeeId}")
    public String adminEditEmployee(@PathVariable int employeeId, HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("employee", managementSoftwareService.getEmployeeById(employeeId));
        model.addAttribute("employeeId", employeeId);

        return "admin-edit-employee";
    }

    @PostMapping("/admin-update-employee/{employeeId}")
    public String adminUpdateEmployee(@PathVariable int employeeId, @ModelAttribute Employee employee, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.editEmployeeById(employeeId, employee);

        return "redirect:/alphaSolutions/admin-employees-page";
    }

    @GetMapping("/admin-employees-page")
    public String viewAdminEmployeesPage(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("employees", managementSoftwareService.getAllEmployees());

        return "admin-employees-page";
    }

    @PostMapping("/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.deleteEmployee(employeeId);
        return "redirect:/alphaSolutions/admin-employees-page";
    }

    @GetMapping("/admin-add-employee")
    public String viewAddEmployee(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("employee", new Employee());
        return "admin-add-employee";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute Employee employee, HttpSession session) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        try {

            String originalUsername = employee.getUsername();

            if (!originalUsername.startsWith("EMP_")) {

                employee.setUsername("EMP_" + originalUsername);

            }
            managementSoftwareService.addEmployee(employee);
        } catch (Exception e) {
            return "redirect:/alphaSolutions/error-duplicate-username-employee";
        }

        return "redirect:/alphaSolutions/admin-employees-page"; // redirect to frontpage for now
    }


    // ADMIN END -----------------------------------------------------------------------------


    //PROJECT MANAGER PROJECT ------------------------------------------------------------------------

    @GetMapping("/projectmanager-frontpage")
    public String viewProjectManagerFrontpage(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        String username = (String) session.getAttribute("username");
        List<Project> projects = managementSoftwareService.getAllProjects();

        // Tilføj map med ansatte pr. projekt
        Map<Integer, List<Employee>> employeeMap = new HashMap<>();
        for (Project project : projects) {
            employeeMap.put(project.getProjectId(), managementSoftwareService.getEmployeesByProjectId(project.getProjectId()));
        }

        model.addAttribute("username", username);
        model.addAttribute("projects", projects);
        model.addAttribute("employeeMap", employeeMap);

        return "projectmanager-frontpage";
    }


    @GetMapping("/projectmanager-add-project")
    public String viewAddProject(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("project", new Project());
        model.addAttribute("employees", managementSoftwareService.getAllEmployees());
        return "projectmanager-add-project";

    }

    @PostMapping("/add-project")
    public String addProject(@ModelAttribute Project project, @RequestParam(value = "selectedEmployees", required = false) List<Integer> selectedEmployeeIds, HttpSession session) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.addProjectWithEmployees(project, selectedEmployeeIds);

        return "redirect:/alphaSolutions/projectmanager-frontpage";
    }

    @GetMapping("/projectmanager-edit-project/{projectId}")
    public String editProjectbyId(@PathVariable int projectId, HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        List<Integer> projectEmployeesIds = new ArrayList<>();
        for(Employee e : managementSoftwareService.getEmployeesByProjectId(projectId)) {
            projectEmployeesIds.add(e.getEmployee_id());
        }


        model.addAttribute("project", managementSoftwareService.getProjectById(projectId));
        model.addAttribute("employees", managementSoftwareService.getAllEmployees());
        model.addAttribute("projectEmployeesIds", projectEmployeesIds);

        return "projectmanager-edit-project";

    }

    @PostMapping("/projectmanager-update-project/{projectId}")
    public String updateProjectByID(@PathVariable int projectId,
                                    @ModelAttribute Project project,
                                    @RequestParam(value = "selectedEmployees", required = false) List<Integer> selectedEmployeeIds,
                                    HttpSession session) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.updateProjectWithEmployees(projectId, project, selectedEmployeeIds);
        return "redirect:/alphaSolutions/projectmanager-frontpage";
    }


    @PostMapping("/projectmanager-delete-project/{projectId}")
    public String deleteProject(@PathVariable int projectId, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.deleteProject(projectId);
        return "redirect:/alphaSolutions/projectmanager-frontpage";

    }
/*
    // Project -----------------------------------------------------------------------------
    @PostMapping("/add-project")
    public String addProject(@ModelAttribute Project project) {
        managementSoftwareService.addProject(project);
        return "redirect:/alphaSolutions";
    }

    @PostMapping("/delete-project/{projectId}")
    public String deleteProject(@PathVariable int projectId) {
        managementSoftwareService.deleteProject(projectId);
        return "redirect:/";
    }

    @PostMapping("/edit-project")
    public String editProject() {
        managementSoftwareService.editProject();
        return "redirect:/";
    }

 */

    // PROJECT MANAGER SUBPROJECT -------------------------------------------------------------------------

    @GetMapping("/projectmanager-project/{projectId}")
    public String viewProject(HttpSession session, @PathVariable int projectId, Model model){
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("project", managementSoftwareService.getProjectByProjectId(projectId));
        model.addAttribute("subprojects", managementSoftwareService.getSubprojectsByProjectId(projectId));


        return "projectmanager-project";
    }

    @GetMapping("/projectmanager-add-subproject/{projectId}")
    public String projectmanagerAddSubproject(HttpSession session, Model model, @PathVariable int projectId) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        Subproject subproject = new Subproject();
        subproject.setProjectId(projectId);

        model.addAttribute("subproject", subproject);

        return "projectmanager-add-subproject";
    }

    @PostMapping("/add-subproject")
    public String addSubproject(HttpSession session, @ModelAttribute Subproject subproject) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.addSubproject(subproject);
        return "redirect:/alphaSolutions/projectmanager-project/" + subproject.getProjectId();
    }

    @PostMapping("/delete-subproject/{subprojectId}/{projectId}")
    public String deleteSubproject(HttpSession session, @PathVariable int subprojectId, @PathVariable int projectId) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.deleteSubproject(subprojectId);
        return "redirect:/alphaSolutions/projectmanager-project/" + projectId;
    }

    @GetMapping("/edit-subproject/{subprojectId}/{projectId}")
    public String editSubproject(HttpSession session, @PathVariable int subprojectId, @PathVariable int projectId, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("subprojectId", subprojectId);
        model.addAttribute("projectId", projectId);
        model.addAttribute("subproject", managementSoftwareService.getSubprojectBySubId(subprojectId));
        return "projectmanager-edit-subproject";
    }

    @PostMapping("/edit-subproject/{subprojectId}/{projectId}")
    public String editSubproject(HttpSession session, @PathVariable int subprojectId, @PathVariable int projectId,
                                 @ModelAttribute Subproject subproject) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("PM")) {
            return "redirect:/alphaSolutions";
        }

        managementSoftwareService.editSubproject(subprojectId, subproject);
        return "redirect:/alphaSolutions/projectmanager-project/" + projectId;
    }

    /*
    // -------------------------------------------------------------------------

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
/*
    @PostMapping("/edit-task")
    public String editTask() {
        managementSoftwareService.editTask();
        return "redirect:/";
    }
*/
//    // Check if done
//    @GetMapping("/check-if-done")
//    public String checkIfDone(Model model) {
//        managementSoftwareService.checkIfDone();
//        return "index";
//    }
}
