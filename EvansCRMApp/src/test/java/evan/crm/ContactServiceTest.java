package evan.crm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

import nl.altindag.log.LogCaptor;

@SpringBootTest
@TestPropertySource(properties = "logging.level.evan.crm=TRACE")
public class ContactServiceTest {
    @Autowired
    private ContactService contactService;

    @Test
    void testProcessContactLogsCorrectly() {
        LogCaptor logCaptor = LogCaptor.forClass(ContactService.class);

        Contact contact = new Contact("name@example.com", "Name");
        contactService.processContact(contact);

        assertTrue(logCaptor.getInfoLogs().stream()
                .anyMatch(log -> log.contains("Processing contact:")));
        assertTrue(logCaptor.getDebugLogs().stream()
                .anyMatch(log -> log.contains("Service name: contactService")));
    }
}
