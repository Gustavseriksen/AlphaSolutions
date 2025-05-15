package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Status;
import org.example.alphasolutions.models.Subproject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubprojectRepository {
    private JdbcTemplate jdbcTemplate;

    public SubprojectRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addSubproject(Subproject subproject) {

        String sql = "INSERT INTO Subprojects (project_id, subproject_name, subproject_description, " +
                "start_date, end_date,subproject_priority, estimated_hours, " +
                "actual_hours_used, subproject_status) VALUES(?,?,?,?,?,?,?,?,?)";

        jdbcTemplate.update(sql, subproject.getProjectId(), subproject.getSubProjectName(),
                subproject.getSubProjectDescription(), subproject.getStartDate(),
                subproject.getEndDate(), subproject.getSubPriority().toString(), subproject.getEstimatedHours(), subproject.getActualHoursUsed(),
                subproject.getStatus().toString());

    }

    public void deleteSubproject(int subProjectId) {
        String sql = "DELETE FROM Subprojects WHERE subproject_id = ?";
        jdbcTemplate.update(sql, subProjectId);
    }

    public void editSubproject(int subprojectId, Subproject subproject) {
        String sql = "UPDATE Subprojects SET " +
                "subproject_name = ?, " +
                "subproject_description = ?, " +
                "start_date = ?, " +
                "end_date = ?, " +
                "subproject_priority = ?, " +
                "estimated_hours = ?, " +
                "actual_hours_used = ?, " +
                "subproject_status = ? " +
                "WHERE subproject_id = ?";

        jdbcTemplate.update(sql,
                subproject.getSubProjectName(),
                subproject.getSubProjectDescription(),
                subproject.getStartDate(),
                subproject.getEndDate(),
                subproject.getSubPriority().toString(),
                subproject.getEstimatedHours(),
                subproject.getActualHoursUsed(),
                subproject.getStatus().toString(),
                subprojectId);
    }


    public List<Subproject> getSubsByProjectId(int projectId) {
        String sql = "SELECT * FROM Subprojects WHERE project_id = ?";

        return jdbcTemplate.query(sql, new Object[]{projectId}, (rs, rowNum) -> new Subproject(
                rs.getInt("subproject_id"),
                rs.getInt("project_id"),
                rs.getString("subproject_name"),
                rs.getString("subproject_description"),
                Priority.valueOf(rs.getString("subproject_priority")),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Status.valueOf(rs.getString("subproject_status"))));
    }

    public Subproject getSubprojectBySubId(int subprojectId) {
        String sql = "SELECT * FROM Subprojects WHERE subproject_id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{subprojectId}, (rs, rowNum) -> new Subproject(
                rs.getInt("subproject_id"),
                rs.getInt("project_id"),
                rs.getString("subproject_name"),
                rs.getString("subproject_description"),
                Priority.valueOf(rs.getString("subproject_priority")),
                rs.getDate("start_date"),
                rs.getDate("end_date"),
                rs.getInt("estimated_hours"),
                rs.getInt("actual_hours_used"),
                Status.valueOf(rs.getString("subproject_status"))
        ));
    }

}
