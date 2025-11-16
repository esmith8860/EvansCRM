package evan.crm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

import nl.altindag.log.LogCaptor;

import java.util.List;
import java.util.Objects;

@SpringBootTest
@TestPropertySource(properties = "logging.level.evan.crm=TRACE")
public class ContactServiceTest {
    @Autowired
    private ContactService contactService;

    @Test
    void testProcessContactLogsCorrectly() {
        LogCaptor logCaptor = LogCaptor.forClass(ContactService.class);

        Contact contact = new Contact("name@example.com", "Name");
        contactService.createContact(contact);

        List<String> processingLogs = logCaptor.getInfoLogs()
                .stream()
                .map(log -> log.contains("Processing contact:") ? log : null)
                .filter(Objects::nonNull)
                .toList();

        List<String> serviceLogs = logCaptor.getInfoLogs()
                .stream()
                .map(log -> log.contains("Service name: contactService") ? log : null)
                .filter(Objects::nonNull)
                .toList();
    }
}
