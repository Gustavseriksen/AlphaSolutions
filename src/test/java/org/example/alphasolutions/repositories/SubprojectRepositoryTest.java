package org.example.alphasolutions.repositories;


import org.example.alphasolutions.models.Priority;
import org.example.alphasolutions.models.Status;
import org.example.alphasolutions.models.Subproject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"classpath:h2init.sql"}
)
class SubprojectRepositoryTest {

    @Autowired
    SubprojectRepository subprojectRepository;

    @Test
    void testGetSubprojectBySubId() {
        Subproject sub = subprojectRepository.getSubprojectBySubId(1);
        assertEquals("Subproject A", sub.getSubProjectName());
        assertEquals(Priority.MEDIUM, sub.getSubPriority());
        assertEquals(Status.COMPLETED, sub.getStatus());
    }

    @Test
    void getSubprojectBySubId_Test() {
        Subproject subproject = subprojectRepository.getSubprojectBySubId(1);
        assertThat(subproject).isNotNull();
        assertThat(subproject.getSubProjectName()).isEqualTo("Subproject A");
    }

    @Test
    void getSubsByProjectId_Test() {
        List<Subproject> subs = subprojectRepository.getSubsByProjectId(1);
        assertThat(subs).isNotEmpty();
        assertThat(subs.getFirst().getSubProjectName()).isEqualTo("Subproject A");
    }

    @Test
    void editSubproject_Test() {
        Subproject subproject = subprojectRepository.getSubprojectBySubId(1);
        subproject.setSubProjectName("Updated Name");
        subproject.setSubProjectDescription("Updated description");
        subproject.setSubPriority(Priority.LOW);
        subproject.setStartDate(Date.valueOf(LocalDate.of(2025, 4, 15)));
        subproject.setEndDate(Date.valueOf(LocalDate.of(2025, 5, 15)));
        subproject.setEstimatedHours(45);
        subproject.setActualHoursUsed(40);
        subproject.setStatus(Status.IN_PROGRESS);

        subprojectRepository.editSubproject(1, subproject);

        Subproject updated = subprojectRepository.getSubprojectBySubId(1);
        assertThat(updated.getSubProjectName()).isEqualTo("Updated Name");
        assertThat(updated.getSubPriority()).isEqualTo(Priority.LOW);
        assertThat(updated.getStatus()).isEqualTo(Status.IN_PROGRESS);
    }

    /*
    skal bruge en getallsubprojects
    @Test
    void deleteSubproject_shouldRemoveSubproject() {
        subprojectRepository.deleteSubproject(1);
        List<Subproject> subs = subprojectRepository.get
        assertTrue();

    }
*/
}