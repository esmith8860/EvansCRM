package evan.crm.api;

import evan.crm.services.ContactService;
import evan.crm.dto.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/evanscrm")
public class APIController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts/{id}")
    public Contact getContactById(@PathVariable UUID id) {
        return contactService.getContactById(id);
    }

    @GetMapping("/contacts")
    public List<Contact> getContacts() {
        return contactService.getContactsList();
    }

    @PutMapping("/contacts/{id}")
    public Contact updateContact(@PathVariable UUID id, @RequestBody Contact contact) {
        return contactService.updateContact(id, contact);
    }

    @DeleteMapping("/contacts/{id}")
    public UUID deleteContact(@PathVariable UUID id) {
        boolean result = contactService.deleteContact(id);

        if (!result) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "Contact not found with id: " + id);
        }

        return id;
    }

    @PostMapping("/contacts")
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    @PatchMapping("/contacts/{id}")
    public Contact patchContact(@PathVariable UUID id, @RequestBody Contact contact) {
        return null;
    }
}
