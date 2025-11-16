package evan.crm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class Contact {
    private UUID id;
    private String name;
    private String email;

    public Contact() {}

    public Contact(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
