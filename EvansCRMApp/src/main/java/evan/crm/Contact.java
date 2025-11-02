package evan.crm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Contact {
    private String name;
    private String email;

    public Contact(String email, String name) {
        this.name = name;
        this.email = email;
    }
}
