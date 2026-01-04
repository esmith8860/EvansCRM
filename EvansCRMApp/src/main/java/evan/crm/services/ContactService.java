package evan.crm.services;
import evan.crm.dto.Contact;

import evan.crm.repository.ContactRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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

    @Autowired
    private Validator validator;

    public Contact createContact(final Contact contact) {
        log.info("Processing contact: {}", contact);

        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }

        final Contact savedContact = contactRepository.save(contact);

        log.info("Contact created with id: {}", savedContact.getId());

        return savedContact;
    }

    public Contact getContactById(final UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }

        log.info("Retrieving contact with id: {}", id);

        final Contact contact = contactRepository.findById(id).orElse(null);

        if (contact == null) {
            log.warn("Contact not found with id: {}", id);
        }

        return contact;
    }

    public List<Contact> getContactsList() {
        return contactRepository.findAll();
    }

    public Contact updateContact(final UUID id, final Contact contact) {
        if (id == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }

        final Contact contactCheck = contactRepository.findById(id).orElse(null);

        if (contactCheck != null) {
            contact.setId(id);

            final Contact updatedContact = contactRepository.save(contact);

            log.info("Contact updated: {}", updatedContact);

            return updatedContact;
        } else {
            log.warn("Contact with id {} not found for update.", id);
            return null;
        }
    }

    public boolean deleteContact(final UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Contact ID cannot be null");
        }

        log.info("Deleting contact with id: {}", id);

        if (!contactRepository.existsById(id)) {
            log.warn("Cannot delete - contact not found with id: {}", id);
            return false;
        }

        contactRepository.deleteById(id);

        log.info("Contact deleted successfully");

        return true;
    }

    private void validateContact(Contact contact) {
        Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
