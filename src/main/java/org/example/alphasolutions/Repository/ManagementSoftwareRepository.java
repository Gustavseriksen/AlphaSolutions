package org.example.alphasolutions.Repository;

import org.example.alphasolutions.Model.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagementSoftwareRepository {

    private JdbcTemplate jdbcTemplate;

    public ManagementSoftwareRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Admin
    public void addProjectManager(ProjectManager projectManager) {
        String sql = "INSERT INTO projectmanagers (username, password) VALUES (?,?)";
        jdbcTemplate.update(sql, projectManager.getUsername(), projectManager.getPassword());
    }

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, password) VALUES (?,?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getPassword());
    }

    public Admin checkCredentials(String username, String password) {
        String sql = "SELECT * FROM Admins WHERE username = ? AND password = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, (rs, rowNum) -> new Admin(
                    rs.getInt("admin_id"),
                    rs.getString("username"),
                    rs.getString("password")));

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //Project:

    public void addProject(Project project) {
        String sql = "INSERT INTO projects (name, description, startDate, endDate) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, project.getProjectName(), project.getProjectDescription(),
                project.getProjectStartDate(), project.getProjectEndDate());

    }

    public void deleteProject(int projectId) {
        String sql = "DELETE FROM projects WHERE project_id = ?";
        jdbcTemplate.update(sql, projectId);

    }

    public void editProject() {
        //er i tvivl om hvordan jeg skal lave den her nu
    }


    public void addSubproject(Subproject subproject) {

        String sql = "INSERT INTO Subprojects (project_id, subproject_name, subproject_description, " +
                "subproject_priority, subproject_start_date, subproject_end_date, estimated_hours, " +
                "actual_hours_used, subproject_status) VALUES(?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, subproject.getProjectID(), subproject.getSubProjectName(),
                subproject.getSubProjectDescription(), subproject.getSubPriority(), subproject.getStartDate(),
                subproject.getEndDate(), subproject.getEstimatedHours(), subproject.getActualHoursUsed(),
                subproject.getStatus());

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


}
