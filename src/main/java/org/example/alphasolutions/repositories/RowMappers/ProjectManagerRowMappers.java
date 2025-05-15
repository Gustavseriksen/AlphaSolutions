package org.example.alphasolutions.repositories.RowMappers;

import org.example.alphasolutions.models.ProjectManager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectManagerRowMappers implements RowMapper<ProjectManager> {
    @Override
    public ProjectManager mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProjectManager(
                rs.getInt("manager_id"),
                rs.getString("username"),
                rs.getString("password")
        );
    }
}
