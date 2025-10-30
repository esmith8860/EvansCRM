package evan.crm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @GetMapping("/api/evanscrm")
    public String testAPI() {
        return "API Test Successful";
    }
}
