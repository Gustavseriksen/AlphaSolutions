package Repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ManagementSoftwareRepository {

    private JdbcTemplate jdbcTemplate;

    public ManagementSoftwareRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Adds ''Workers''
    //TODO
    public void addProjectManager() {

    }

    //TODO
    public void addEmployee() {

    }

    //Project:

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


    //Task:

    //TODO
    public void addTask(){

    }

    //TODO
    public void deleteTask(){

    }

    //TODO
    public void editTask(){

    }


    //Checks if project/subproject/task is done
    //TODO
    public void checkIfDone (){

    }

}
