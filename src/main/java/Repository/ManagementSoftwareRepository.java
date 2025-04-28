package Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ManagementSoftwareRepository {

    private JdbcTemplate jdbcTemplate;

    public ManagementSoftwareRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO
    public void addProjectManager() {

    }

    //TODO
    public void addEmployee() {

    }

    //TODO
    public void addProject(){

    }

    //TODO
    public void deleteProject(){

    }

    //TODO
    public void editProject(){

    }


    //Subproject:

    //TODO
    public void addSubproject(){

    }

    //TODO
    public void deleteSubproject(){

    }

    //TODO
    public void editSubproject(){

    }

}
