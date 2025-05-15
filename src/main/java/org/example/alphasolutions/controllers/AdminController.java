package org.example.alphasolutions.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.models.ProjectManager;
import org.example.alphasolutions.services.EmployeeService;
import org.example.alphasolutions.services.ProjectManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("alphaSolutions/admin")
public class AdminController {

    private final ProjectManagerService projectManagerService;
    private final EmployeeService employeeService;

    public AdminController(ProjectManagerService projectManagerService, EmployeeService employeeService) {
        this.projectManagerService = projectManagerService;
        this.employeeService = employeeService;
    }

    //Admin frontpage
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

    //Project managers page
    @GetMapping("/admin-projectmanagers-page")
    public String viewAdminProjectManagersPage(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("projectManagers", projectManagerService.getAllProjectManagers());

        return "admin-projectmanagers-page";
    }

    //Edit project managers page
    @GetMapping("/admin-edit-projectmanager/{projectManagerId}")
    public String adminEditProjectManager(@PathVariable int projectManagerId, HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("projectManager", projectManagerService.getProjectManagerById(projectManagerId));
        model.addAttribute("projectManagerId", projectManagerId);

        return "admin-edit-projectmanager";
    }

    //Update project manager
    @PostMapping("/admin-update-projectmanager/{projectManagerId}")
    public String adminUpdateProjectManager(@PathVariable int projectManagerId, @ModelAttribute ProjectManager projectManager, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        projectManagerService.editProjectManagerById(projectManagerId, projectManager);
        return "redirect:/alphaSolutions/admin-projectmanagers-page";
    }

    //Delete project manager
    @PostMapping("/deleteProjectManager/{projectManagerId}")
    public String deleteProjectManager(@PathVariable int projectManagerId, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        projectManagerService.deleteProjectManager(projectManagerId);
        return "redirect:/alphaSolutions/admin-projectmanagers-page";
    }

    //Add project manager
    @GetMapping("/admin-add-projectmanager")
    public String viewAddProjectManager(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("projectmanager", new ProjectManager());

        return "admin-add-projectmanager";
    }

    //post to add project manager
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
            projectManagerService.addProjectManager(projectManager);
        } catch (Exception e) {
            return "redirect:/alphaSolutions/error-duplicate-username-projectmanager";
        }

        return "redirect:/alphaSolutions/admin-projectmanagers-page"; // redirect to frontpage for now
    }

    //PROJECT MANAGER END

    //Add employee
    @GetMapping("/admin-add-employee")
    public String viewAddEmployee(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");
        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("employee", new Employee());
        return "admin-add-employee";
    }

    //Post to add employee
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
            employeeService.addEmployee(employee);
        } catch (Exception e) {
            return "redirect:/alphaSolutions/error-duplicate-username-employee";
        }

        return "redirect:/alphaSolutions/admin-employees-page"; // redirect to frontpage for now
    }

    //Employee page
    @GetMapping("/admin-employees-page")
    public String viewAdminEmployeesPage(HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("employees", employeeService.getAllEmployees());

        return "admin-employees-page";
    }

    //Employee edit page
    @GetMapping("/admin-edit-employee/{employeeId}")
    public String adminEditEmployee(@PathVariable int employeeId, HttpSession session, Model model) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("employeeId", employeeId);

        return "admin-edit-employee";
    }

    //Update employee
    @PostMapping("/admin-update-employee/{employeeId}")
    public String adminUpdateEmployee(@PathVariable int employeeId, @ModelAttribute Employee employee, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        employeeService.editEmployeeById(employeeId, employee);

        return "redirect:/alphaSolutions/admin-employees-page";
    }


    //Delete employee
    @PostMapping("/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId, HttpSession session) {
        String ID = (String) session.getAttribute("ID");

        if (ID == null || !ID.endsWith("ADM")) {
            return "redirect:/alphaSolutions";
        }

        employeeService.deleteEmployee(employeeId);
        return "redirect:/alphaSolutions/admin-employees-page";
    }


}
