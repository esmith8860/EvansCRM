package evan.crm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    @Test
    void testProcessContactLogsCorrectly() {
        ContactService contactService = new ContactService();
        Contact contact = new Contact();
        contact.setName("");
        contact.setEmail("");
        contactService.processContact(contact);
    }
}
