package org.example.alphasolutions.repositories;

import org.example.alphasolutions.models.Subproject;
import org.example.alphasolutions.rowMappers.SubprojectRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
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

        return jdbcTemplate.query(sql, new SubprojectRowMapper(), projectId);
    }

    public Subproject getSubprojectBySubId(int subprojectId) {
        String sql = "SELECT * FROM Subprojects WHERE subproject_id=?";

        try {
            // Bruger den nye SubprojectRowMapper.
            // Sender subprojectId med som argument til SQL-forespørgslen.
            return jdbcTemplate.queryForObject(sql, new SubprojectRowMapper(), subprojectId);
        } catch (EmptyResultDataAccessException e) {
            // Hvis queryForObject kaster EmptyResultDataAccessException (fordi intet subprojekt blev fundet),
            // returnerer vi null for at indikere, at subprojektet ikke eksisterer.
            System.err.println("Subproject with ID " + subprojectId + " not found. Returning null. Error: " + e.getMessage());
            return null;
        }
    }

}
