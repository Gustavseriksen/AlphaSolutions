package org.example.alphasolutions.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.alphasolutions.models.Admin;
import org.example.alphasolutions.models.Employee;
import org.example.alphasolutions.models.ProjectManager;
import org.example.alphasolutions.services.CredentialsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/alphaSolutions/auth")
public class AuthController {

    private final CredentialsService credentialsService;

    public AuthController(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @PostMapping("/checkCredentials")
    public String checkCredentials(@RequestParam String username,
                                   @RequestParam String password,
                                   HttpSession session) {

        if (username.startsWith("ADM")) {
            Admin admin = credentialsService.checkAdminCredentials(username, password);
            if (admin != null) {
                session.setAttribute("ID", admin.getAdmin_id() + "ADM");
                session.setAttribute("username", admin.getUsername());
                return "redirect:/alphaSolutions/admin/admin-frontpage";
            } else {
                return "index"; // eller /index hvis det er din login-side
            }

        } else if (username.startsWith("PM")) {
            ProjectManager projectManager = credentialsService.checkProjectManagerCredentials(username, password);
            if (projectManager != null) {
                session.setAttribute("ID", projectManager.getProjectManagerId() + "PM");
                session.setAttribute("username", projectManager.getUsername());
                return "redirect:/alphaSolutions/pm/projectmanager-frontpage";
            } else {
                return "index"; // eller /index hvis det er din login-side
            }

        } else if (username.startsWith("EMP")) {
            Employee employee = credentialsService.checkEmployeeCredentials(username, password);
            if (employee != null) {
                session.setAttribute("ID", employee.getEmployee_id() + "EMP");
                session.setAttribute("username", employee.getUsername());
                return "redirect:/alphaSolutions/emp/employee-frontpage";
            } else {
                return "redirect:/alphaSolutions"; // eller /index hvis det er din login-side
            }
        } else {
            return "index";
        }
    }


    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // sletter hele sessionen
        return "index"; // tilbage til login eller forside
    }

    @GetMapping("/error-duplicate-username-employee")
    public String getErrorDuplicateUsernameEmployee() {
        return "error-duplicate-username-employee";
    }

    @GetMapping("/error-duplicate-username-projectmanager")
    public String getErrorDuplicateUsernameProjectmanager() {
        return "error-duplicate-username-projectmanager";
    }
}
