package evan.crm;

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
    void contactsIsNotNull() {
        assertFalse(contactRepository.isEmpty());
    }

    @Test
    void contactExists() {
        Contact contact = new Contact("Example Person", "example@email.com");
        UUID id = contactRepository.createContact(contact);
        assertTrue(contactRepository.isContactExists(id));
        assertEquals("Example Person", contactRepository.findContactById(id).getName());
    }

    @Test
    void contactDeletion() {
        Contact contact = new Contact("deletecontact@email.com", "Delete Me");
        UUID id = contactRepository.createContact(contact);
        assertTrue(contactRepository.isContactExists(id));
        assertTrue(contactRepository.deleteContact(id));
    }
}
