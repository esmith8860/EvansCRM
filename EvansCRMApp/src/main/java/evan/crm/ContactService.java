package evan.crm;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {
    @Getter @Setter
    private String serviceName = "contactService";

    public void processContact(Contact contact) {
        log.info("Processing contact: {}", contact);
        log.debug("Service name: {}", getServiceName());
    }
}
