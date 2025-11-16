package evan.crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/evanscrm")
public class APIController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts/{id}")
    public Contact getContactById(UUID id) {
        return contactService.getContactById(id);
    }

    @GetMapping("/contacts")
    public String getContacts() {
        return "List of contacts: " + contactService.getContactsList();
    }

    @PutMapping("/contacts/{id}")
    public String updateContact(@PathVariable UUID id, String name, String email) {
        return "Contact updated: " + contactService.updateContact(id, name, email);
    }

    @DeleteMapping("/contacts/{id}")
    public String deleteContact(@PathVariable UUID id) {
        contactService.deleteContact(id);
        return "Contact deleted with id: " + id;
    }

    @PostMapping("/contacts/create")
    public String createContact(String name, String email) {
        Contact contact = contactService.createContact(new Contact(name, email));
        return "Contact created: " + contact;
    }
}
