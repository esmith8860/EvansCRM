package evan.crm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Contact {
    private String email;
    private String name;

    public Contact(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
