package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Project;
import org.example.alphasolutions.models.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ProjectRepository {
    private JdbcTemplate jdbcTemplate;

    public ProjectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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



    public List<Project> getProjectsByEmployeeId(int employeeId) {

        String sql = "SELECT p.* FROM Projects p " +
                "JOIN EmployeeProjects ep ON p.project_id = ep.project_id " +
                "WHERE ep.employee_id = ?";


        return jdbcTemplate.query(sql, (rs, rowNum) -> new Project(
                rs.getInt("project_id"),
                rs.getString("project_name"),
                rs.getString("project_description"),
                rs.getObject("start_date", LocalDate.class),
                rs.getObject("end_date", LocalDate.class),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Priority.valueOf(rs.getString("project_priority")),
                Status.valueOf(rs.getString("project_status"))
        ), employeeId);
    }


}
