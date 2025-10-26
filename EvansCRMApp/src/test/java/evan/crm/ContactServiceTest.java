package evan.crm;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import nl.altindag.log.LogCaptor;

public class ContactServiceTest {

    @Test
    void testProcessContactLogsCorrectly() {
        LogCaptor logCaptor = LogCaptor.forClass(ContactService.class);
        ContactService contactService = new ContactService();
        Contact contact = new Contact();
        contact.setName("");
        contact.setEmail("");
        contactService.processContact(contact);

        assertTrue(logCaptor.getInfoLogs().stream()
                .anyMatch(log -> log.contains("Processing contact:")));
        assertTrue(logCaptor.getDebugLogs().stream()
                .anyMatch(log -> log.contains("Service name: contactService")));
    }
}
