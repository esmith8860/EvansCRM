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
    public String updateContact(@PathVariable UUID id, @RequestBody Contact contact) {
        return "Contact updated: " + contactService.updateContact(id, contact);
    }

    @DeleteMapping("/contacts/{id}")
    public String deleteContact(@PathVariable UUID id) {
        contactService.deleteContact(id);
        return "Contact deleted with id: " + id;
    }

    @PostMapping("/contacts/create")
    public String createContact(@RequestBody Contact contact) {
        Contact updatedContact = contactService.createContact(contact);
        return "Contact created: " + updatedContact;
    }
}
