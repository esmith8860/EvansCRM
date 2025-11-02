package evan.crm;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ApplicationProperties {
    private Integer maxContactNameCharacters;
    private Integer maxContactEmailCharacters;

    @Value("${contact.service.name}")
    private String contactServiceName;
}
