package evan.crm.repository;

import evan.crm.dto.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = "logging.level.evan.crm=TRACE")
public class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;

    @Test
    void contactsRepositoryIsNotNull() {
        assertNotNull(contactRepository);
    }

    @Test
    void contactExists() {
        Contact contact = new Contact("Example Person", "example@email.com");
        Contact savedContact = contactRepository.save(contact);
        UUID id = savedContact.getId();

        assertTrue(contactRepository.existsById(id));
        assertEquals("Example Person", contactRepository.findById(id).orElseThrow().getName());
    }

    @Test
    void contactDeletion() {
        Contact contact = new Contact("Delete Me", "deletecontact@email.com");
        Contact savedContact = contactRepository.save(contact);
        UUID id = savedContact.getId();

        assertTrue(contactRepository.existsById(id));
        contactRepository.deleteById(id);
        assertFalse(contactRepository.existsById(id));
    }

    @Test
    void contactUpdate() {
        Contact contact = new Contact("Update Me", "updateme@email.com");
        Contact savedContact = contactRepository.save(contact);
        UUID id = savedContact.getId();

        assertTrue(contactRepository.existsById(id));

        Contact updatedContact = new Contact("Updated Contact", "updated@email.com");
        updatedContact.setId(id);
        contactRepository.save(updatedContact);

        Contact retrieved = contactRepository.findById(id).orElseThrow();
        assertEquals("Updated Contact", retrieved.getName());
        assertEquals("updated@email.com", retrieved.getEmail());
    }
}
