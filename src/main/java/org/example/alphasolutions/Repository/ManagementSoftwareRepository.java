package org.example.alphasolutions.Repository;

import org.example.alphasolutions.Model.*;
import org.example.alphasolutions.Repository.RowMappers.AdminRowMappers;
import org.example.alphasolutions.Repository.RowMappers.EmployeeRowMappers;
import org.example.alphasolutions.Repository.RowMappers.ProjectManagerRowMappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ManagementSoftwareRepository {

    private JdbcTemplate jdbcTemplate;

    public ManagementSoftwareRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //ADMIN -------------------------------------------------------------------------------------

    // ADMIN PROJECT MANAGER -------------------------------------------------------------------------------------
    public void addProjectManager(ProjectManager projectManager) {
        String sql = "INSERT INTO projectmanagers (username, password) VALUES (?,?)";
        jdbcTemplate.update(sql, projectManager.getUsername(), projectManager.getPassword());
    }

    public Admin checkAdminCredentials(String username, String password) {
        String sql = "SELECT * FROM Admins WHERE username = ? AND password = ?";

        try { // RowMapper der bliver brugt under AdminRowMappers:
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new AdminRowMappers());

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public ProjectManager checkProjectManagerCredentials(String username, String password) {
        String sql = "SELECT * FROM ProjectManagers WHERE username = ? AND password = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new ProjectManagerRowMappers());


        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void deleteProjectManager(int projectManagerId) {
        String sql = "DELETE FROM ProjectManagers WHERE manager_id = ?";

        jdbcTemplate.update(sql, projectManagerId);
    }

    public List<ProjectManager> getAllProjectManagers() {
        String sql = "SELECT * FROM ProjectManagers";

        return jdbcTemplate.query(sql, new ProjectManagerRowMappers());

    }

    public ProjectManager getProjectManagerById(int projectManagerId) {
        String sql = "SELECT * FROM ProjectManagers WHERE manager_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{projectManagerId}, new ProjectManagerRowMappers());

    }

    public void editProjectManagerById(int projectManagerId, ProjectManager projectManager) {
        String sql = "UPDATE ProjectManagers SET  " +
                "username = ?, password = ? WHERE manager_id = ?";
        jdbcTemplate.update(sql, projectManager.getUsername(), projectManager.getPassword(), projectManagerId);
    }

    // ADMIN EMPLOYEE -------------------------------------------------------------------------------------

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO Employees (username, password) VALUES (?,?)";
        jdbcTemplate.update(sql, employee.getUsername(), employee.getPassword());
    }

    public Employee checkEmployeeCredentials(String username, String password) {
        String sql = "SELECT * FROM Employees WHERE username = ? AND password = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, new EmployeeRowMappers());

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM Employees";
        // Her genbruger vi rowmappers fra klassen EmployeeRowMappers:
        return jdbcTemplate.query(sql, new EmployeeRowMappers());
    }

    public Employee getEmployeeById(int employeeId) {
        String sql = "SELECT * FROM Employees WHERE employee_id = ?";

        // Her genbruger vi kode inde fra EmployeeRowMappers-klassen:
        return jdbcTemplate.queryForObject(sql, new Object[]{employeeId}, new EmployeeRowMappers());

    }

    public void editEmployeeById(int employeeId, Employee employee) {
        String sql = "UPDATE Employees SET  " +
                "username = ?, password = ? WHERE employee_id = ?";
        jdbcTemplate.update(sql, employee.getUsername(), employee.getPassword(), employeeId);
    }

    public void deleteEmployee(int employeeId) {
        String sql = "DELETE FROM Employees WHERE employee_id = ?";

        jdbcTemplate.update(sql, employeeId);
    }

    // ADMIN END -------------------------------------------------------------------------------------

    //PROJECT MANAGER, PROJECT -------------------------------------------------------------------------------------

    public void addProject(Project project) {
        String sql = "INSERT INTO projects (project_name, project_description, start_date, end_date, estimated_hours, actual_hours_used, project_priority, project_status) VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, project.getProjectName(),
                project.getProjectDescription(),
                project.getProjectStartDate(),
                project.getProjectEndDate(),
                project.getEstimatedHours(),
                project.getActualHoursUsed(),
                project.getProjectPriority().toString(),
                project.getProjectStatus().toString());

    }


    public void deleteProject(int projectId) {
        String sql = "DELETE FROM projects WHERE project_id = ?";
        jdbcTemplate.update(sql, projectId);

    }

    public void editProject() {
        //er i tvivl om hvordan jeg skal lave den her nu
    }

    public List<Project> getAllProjects() {
        String sql = "SELECT * FROM Projects";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Project(
                rs.getInt("project_id"),
                rs.getString("project_name"),
                rs.getString("project_description"),
                rs.getObject("start_date", LocalDate.class),
                rs.getObject("end_date", LocalDate.class),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Priority.valueOf(rs.getString("project_priority")),
                Status.valueOf(rs.getString("project_status")
                )));
    }

    public Project getProjectByProjectId(int projectId) {

        String sql = "SELECT * FROM projects WHERE project_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{projectId}, (rs, rowNum) -> new Project(
                rs.getInt("project_id"),
                rs.getString("project_name"),
                rs.getString("project_description"),
                rs.getObject("start_date", LocalDate.class),
                rs.getObject("end_date", LocalDate.class),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Priority.valueOf(rs.getString("project_priority")),
                Status.valueOf(rs.getString("project_status")
                )));
    }

    public int getLastInsertedProjectId() {
        String sql = "SELECT LAST_INSERT_ID()";
        return jdbcTemplate.queryForObject(sql, Integer.class);

    }

    public void assignEmployeeToProject(int employeeId, int projectId) {
        String sql = "INSERT INTO EmployeeProjects (employee_id, project_id) VALUES(?,?)";
        jdbcTemplate.update(sql, employeeId, projectId);
    }

    public List<Employee> getEmployeesByProjectId(int projectId) {
        String sql = "SELECT e.* FROM Employees e " +
                "JOIN EmployeeProjects ep ON e.employee_id = ep.employee_id " +
                "WHERE ep.project_id = ?";
        return jdbcTemplate.query(sql, new EmployeeRowMappers(), projectId);
    }
    // PROJECT MANAGER, SUBPROJECT -------------------------------------------------------------------------------------

    public void addSubproject(Subproject subproject) {

        String sql = "INSERT INTO Subprojects (project_id, subproject_name, subproject_description, " +
                "subproject_priority, subproject_start_date, subproject_end_date, estimated_hours, " +
                "actual_hours_used, subproject_status) VALUES(?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, subproject.getProjectID(), subproject.getSubProjectName(),
                subproject.getSubProjectDescription(), subproject.getSubPriority(), subproject.getStartDate(),
                subproject.getEndDate(), subproject.getEstimatedHours(), subproject.getActualHoursUsed(),
                subproject.getStatus().toString());

    }

    public void deleteSubproject(int subProjectId) {
        String sql = "DELETE FROM Subprojects WHERE subproject_id = ?";
        jdbcTemplate.update(sql, subProjectId);
    }

    public void editSubproject(int subprojectId, Subproject subproject) {
        String sql = "UPDATE Subprojects SET  " +
                "subproject_name = ?, subproject_description = ?, subproject_priority = ?, subproject_start_date = ?," +
                " subproject_end_date = ?, estimated_hours = ?, actual_hours_used = ?, subproject_status = ?" +
                "WHERE subproject_id = ?";
        jdbcTemplate.update(sql, subproject.getSubProjectName(), subproject.getSubProjectDescription(),
                subproject.getSubPriority(), subproject.getStartDate(), subproject.getEndDate(),
                subproject.getEstimatedHours(), subproject.getActualHoursUsed(), subproject.getStatus(),
                subprojectId);
    }

    public List<Subproject> getSubsByProjectId(int projectId) {
        String sql = "SELECT * FROM Subprojects WHERE project_id = ?";

        return jdbcTemplate.query(sql, new Object[]{projectId}, (rs, rowNum) -> new Subproject(
                rs.getInt("subproject_id"),
                rs.getInt("project_id"),
                rs.getString("subproject_name"),
                rs.getString("subproject_description"),
                rs.getString("subproject_priority"),
                rs.getDate("subproject_start_date"),
                rs.getDate("subproject_end_date"),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Status.valueOf(rs.getString("subproject_status"))));
    }

    //Task:

    public void addTask(Task task) {
        String sql = "INSERT INTO Tasks (subproject_id, task_name, task_description, task_priority," +
                "task_start_date, task_end_date, estimated_hours, actual_hours_used, task_status) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, task.getSubProjectId(), task.getTaskName(), task.getDescription(),
                task.getPriority(), task.getStartDate(), task.getEndDate(), task.getEstimatedHours(),
                task.getActualUsedHours(), task.getStatus());
    }

    public void deleteTask(int taskId) {
        String sql = "DELETE FROM Tasks WHERE task_id = ?";
        jdbcTemplate.update(sql, taskId);
    }

    public void editTask(int taskId, Task task) {
        String sql = "UPDATE Tasks SET " +
                "task_name = ?, task_description = ?, task_priority = ?, task_start_date = ?, " +
                " task_end_date = ?, estimated_hours = ?, actual_hours_used = ?, task_status = ?" +
                "WHERE task_id = ?";
        jdbcTemplate.update(sql, task.getTaskName(), task.getDescription(), task.getPriority(), task.getStartDate(),
                task.getEndDate(), task.getEstimatedHours(), task.getActualUsedHours(), task.getStatus(), taskId);
    }

    public List<Task> getTasksBySubId(int subId) {
        String sql = "SELECT * FROM Tasks WHERE subproject_id = ?";

        return jdbcTemplate.query(sql, new Object[]{subId}, (rs, rowNum) -> new Task(
                rs.getInt("task_id"),
                rs.getInt("subproject_id"),
                rs.getString("task_name"),
                rs.getString("task_description"),
                rs.getString("task_priority"),
                rs.getDate("task_start_date"),
                rs.getDate("task_end_date"),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Status.valueOf(rs.getString("task_status"))));
    }


    public Project getProjectById(int projectId) {
        String sql = "SELECT * FROM Projects WHERE project_id =?";
        return jdbcTemplate.queryForObject(sql, new Object[]{projectId}, (rs, rowNum) -> new Project(
                rs.getInt("project_id"),
                rs.getString("project_name"),
                rs.getString("project_description"),
                rs.getObject("start_date", LocalDate.class),
                rs.getObject("end_date", LocalDate.class),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Priority.valueOf(rs.getString("project_priority")),
                Status.valueOf(rs.getString("project_status"))
        ));
    }

    public void updateProject(int projectId, Project project) {
        String sql = "UPDATE Projects SET project_name = ?, project_description = ?, start_date = ?, end_date = ?, estimated_hours = ?, actual_hours_used = ?, project_priority = ?, project_status = ? WHERE project_id = ?";
        jdbcTemplate.update(sql,
                project.getProjectName(),
                project.getProjectDescription(),
                project.getProjectStartDate(),
                project.getProjectEndDate(),
                project.getEstimatedHours(),
                project.getActualHoursUsed(),
                project.getProjectPriority().toString(),
                project.getProjectStatus().toString(),
                projectId
        );
    }

    public void deleteAllEmployeeAssignmentsFromProject(int projectId) {
        String sql = "DELETE FROM EmployeeProjects WHERE project_id = ?";
        jdbcTemplate.update(sql, projectId);
    }
}
