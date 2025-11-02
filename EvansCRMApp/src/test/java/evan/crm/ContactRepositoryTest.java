package evan.crm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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
        Contact contact = new Contact("example@email.com", "Example Person");
        contactRepository.createContact(contact);
        assertTrue(contactRepository.isContactExists("example@email.com"));
        assertEquals("Example Person", contactRepository.findContactByEmail("example@email.com"));
    }

    @Test
    void contactDeletion() {
        Contact contact = new Contact("deletecontact@email.com", "Delete Me");
        contactRepository.createContact(contact);
        assertTrue(contactRepository.isContactExists("deletecontact@email.com"));
        contactRepository.deleteContact("deletecontact@email.com");
        assertFalse(contactRepository.isContactExists("deletecontact@email.com"));
    }
}
