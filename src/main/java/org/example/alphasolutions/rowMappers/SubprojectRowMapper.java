package org.example.alphasolutions.rowMappers;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Status;
import org.example.alphasolutions.models.Subproject;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SubprojectRowMapper implements RowMapper<Subproject> {

    @Override
    public Subproject mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Subproject(
                rs.getInt("subproject_id"),
                rs.getInt("project_id"),
                rs.getString("subproject_name"),
                rs.getString("subproject_description"),
                Priority.valueOf(rs.getString("subproject_priority")),
                rs.getObject("start_date", LocalDate.class),
                rs.getObject("end_date", LocalDate.class),
                rs.getDouble("estimated_hours"),
                rs.getDouble("actual_hours_used"),
                Status.valueOf(rs.getString("subproject_status"))
        );
    }
}