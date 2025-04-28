package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagementSoftwareController {

    //Front Page View
    @GetMapping("")

    public String viewFrontPage() {
        return "index";
    }
}
