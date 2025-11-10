package evan.crm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//import java.util.UUID;

@Data
@Getter
@Setter
public class Contact {
    //private UUID uuid;
    private String name;
    private String email;

    public Contact() {}

    //Add UUID as a parameter as well?
    public Contact(String name, String email) {
        //this.uuid = uuid;
        this.name = name;
        this.email = email;
    }
}
