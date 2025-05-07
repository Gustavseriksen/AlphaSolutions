package org.example.alphasolutions.Controller;

import org.example.alphasolutions.Service.ManagementSoftwareService;
import jakarta.servlet.http.HttpSession;
import org.example.alphasolutions.Model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    // Admin -----------------------------------------------------------------------------

    @PostMapping("/checkCredentials")
    public String checkCredentials(@RequestParam String username,
                                   @RequestParam String password,
                                   HttpSession session) {

        if (username.startsWith("ADM")) {
            Admin admin = managementSoftwareService.checkAdminCredentials(username, password);
            if (admin != null) {
                session.setAttribute("ID", admin.getAdmin_id());
                return "redirect:/alphaSolutions/admin-frontpage";
            } else {
                return "redirect:/alphaSolutions"; // eller /index hvis det er din login-side
            }

        } else if (username.startsWith("PM")) {
            ProjectManager projectManager = managementSoftwareService.checkProjectManagerCredentials(username, password);
            if (projectManager != null) {
                session.setAttribute("ID", projectManager.getProjectManagerId());
                return "redirect:/alphaSolutions/admin-frontpage";
            } else {
                return "redirect:/alphaSolutions"; // eller /index hvis det er din login-side
            }

        } else if (username.startsWith("EMP")) {
            Employee employee = managementSoftwareService.checkEmployeeCredentials(username, password);
            if (employee != null) {
                session.setAttribute("ID", employee.getEmployee_id());
                return "redirect:/alphaSolutions/admin-frontpage";
            } else {
                return "redirect:/alphaSolutions"; // eller /index hvis det er din login-side
            }
        } else {
            return "redirect:/alphaSolutions";
        }

    }

    @GetMapping("/admin-frontpage")
    public String viewAdminFrontPage(HttpSession session) {
        Integer ID = (Integer) session.getAttribute("ID");

        if (ID == null) {
            return "redirect:/alphaSolutions";
        }
        return "admin-frontpage";
    }

    @GetMapping("/admin-projectmanagers-page")
    public String viewAdminProjectManagersPage(HttpSession session, Model model) {
        Integer ID = (Integer) session.getAttribute("ID");

        if (ID == null) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("projectManagers", managementSoftwareService.getAllProjectManagers());

        return "admin-projectmanagers-page";
    }

    @GetMapping("/admin-edit-projectmanager/{projectManagerId}")
    public String adminEditProjectManager(@PathVariable int projectManagerId, HttpSession session, Model model) {
        Integer ID = (Integer) session.getAttribute("ID");

        if (ID == null) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("projectManager", managementSoftwareService.getProjectManagerById(projectManagerId));
        model.addAttribute("projectManagerId", projectManagerId);

        return "admin-edit-projectmanager";
    }

    @PostMapping("/admin-update-projectmanager/{projectManagerId}")
    public String adminUpdateProjectManager(@PathVariable int projectManagerId, @ModelAttribute ProjectManager projectManager) {
        managementSoftwareService.editProjectManagerById(projectManagerId, projectManager);

        return "redirect:/alphaSolutions/admin-projectmanagers-page";
    }

    @GetMapping("/admin-employees-page")
    public String viewAdminEmployeesPage(HttpSession session) {
        Integer ID = (Integer) session.getAttribute("ID");

        if (ID == null) {
            return "redirect:/alphaSolutions";
        }

        return "admin-employees-page";
    }


    @PostMapping("/deleteProjectManager/{projectManagerId}")
    public String deleteProjectManager(@PathVariable int projectManagerId) {
        managementSoftwareService.deleteProjectManager(projectManagerId);
        return "redirect:/alphaSolutions/admin-projectmanagers-page";
    }

    // Adding a pm & emp ---------------------------------------------------------------------
    @GetMapping("/admin-add-projectmanager")
    public String viewAddProjectManager(HttpSession session, Model model) {
        Integer ID = (Integer) session.getAttribute("ID");

        if (ID == null) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("projectmanager", new ProjectManager());

        return "admin-add-projectmanager";
    }

    @PostMapping("/add-projectmanager")
    public String addProjectManager(@ModelAttribute ProjectManager projectManager, HttpSession session) {
        Integer ID = (Integer) session.getAttribute("ID");

        if (ID == null) {
            return "redirect:/alphaSolutions";
        }

        try {

        String originalUsername = projectManager.getUsername();

        if (!originalUsername.startsWith("PM_")) {

            projectManager.setUsername("PM_" + originalUsername);

        }
            managementSoftwareService.addProjectManager(projectManager);
        } catch (Exception e) {
            return "redirect:/alphaSolutions/error-duplicate-username";
        }

        return "redirect:/alphaSolutions/admin-projectmanagers-page"; // redirect to frontpage for now
    }

    @GetMapping("/error-duplicate-username")
    public String getAdminProjectManagersPage() {
        return "error-duplicate-username";
    }

    @PostMapping("/add-employee")
    public String addEmployee(@ModelAttribute Employee employee) {
        managementSoftwareService.addEmployee(employee);
        return "redirect:/";
    }

    // Admin End -----------------------------------------------------------------------------


    // Project -----------------------------------------------------------------------------
    @PostMapping("/add-project")
    public String addProject(@ModelAttribute Project project) {
        managementSoftwareService.addProject(project);
        return "redirect:/";
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

    // Subproject -------------------------------------------------------------------------
    @PostMapping("/add-subproject")
    public String addSubproject(@ModelAttribute Subproject subproject) {
        managementSoftwareService.addSubproject(subproject);
        return "redirect:/";
    }

    @PostMapping("/delete-subproject/{subprojectId}")
    public String deleteSubproject(@PathVariable int subprojectId) {
        managementSoftwareService.deleteSubproject(subprojectId);
        return "redirect:/";
    }

    @PostMapping("/edit-subproject/{subprojectId}")
    public String editSubproject(@PathVariable int subprojectId, Subproject subproject) {
        managementSoftwareService.editSubproject(subprojectId, subproject);
        return "redirect:/";
    } // -------------------------------------------------------------------------

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
