package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.ProjectManager;
import org.example.alphasolutions.repositories.RowMappers.ProjectManagerRowMappers;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectManagerRepository {

    private JdbcTemplate jdbcTemplate;

    public ProjectManagerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProjectManager(ProjectManager projectManager) {
        String sql = "INSERT INTO projectmanagers (username, password) VALUES (?,?)";
        jdbcTemplate.update(sql, projectManager.getUsername(), projectManager.getPassword());
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
}
