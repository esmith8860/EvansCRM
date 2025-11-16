package evan.crm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ContactService {
    @Value("${contact.service.name:contactService}")
    private String serviceName;

    public ContactService(@Value("${contact.service.name:contactService}") String serviceName) {
        this.serviceName = serviceName;
    }

    @Autowired
    private ContactRepository contactRepository;

    public Contact createContact(Contact contact) {
        log.info("Processing contact: {}", contact);
        log.debug("Service name: {}", serviceName);

        UUID id = contactRepository.createContact(contact);
        log.info("Contact created with id: {}", id);
        return contactRepository.findContactById(id);
    }

    public Contact getContactById(UUID id) {
        log.info("Retrieving contact with id: {}", id);
        Contact contact = contactRepository.findContactById(id);

        if (contact == null) {
            log.warn("Contact not found with id: {}", id);
        }

        return contact;
    }

    public String getContactsList() {
        return contactRepository.getContactsList();
    }

    public Contact updateContact(UUID id, Contact contact) {
        Contact contactCheck = contactRepository.findContactById(id);
        if (contactCheck != null) {
            contactRepository.updateContact(contact);
            Contact updatedContact = contactRepository.findContactById(id);
            log.info("Contact updated: {}", updatedContact);
            return contact;
        } else {
            log.warn("Contact with id {} not found for update.", id);
            return null;
        }
    }

    public boolean deleteContact(UUID id) {
        log.info("Deleting contact with id: {}", id);

        if (!contactRepository.isContactExists(id)) {
            log.warn("Cannot delete - contact not found with id: {}", id);
            return false;
        }

        boolean deleted = contactRepository.deleteContact(id);
        log.info("Contact deleted successfully: {}", deleted);
        return deleted;
    }
}
