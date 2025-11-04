package evan.crm;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {
    //@Value("${contact.service.name:contactService}")
    private String serviceName;

    public ContactService(@Value("${contact.service.name:contactService}") String serviceName) {
        this.serviceName = serviceName;
    }

    public void processContact(Contact contact) {
        log.info("Processing contact: {}", contact);
        log.debug("Service name: {}", serviceName);
    }
}
