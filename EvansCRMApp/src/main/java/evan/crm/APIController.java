package evan.crm;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @GetMapping("/api/evanscrm")
    public String crmAPI() {
        return "API Test Successful";
    }

    // Creates a contact
    @GetMapping("/api/evanscrm/contact")
    public String testContactAPI(String email, String name) {
        Contact contact = new Contact(email, name);
        return "Contact created: " + contact.toString();
    }
}
