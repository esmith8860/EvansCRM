package evan.crm;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    private final HashMap<String, String> contactsList;

    @Getter
    private static final ContactRepository instance = new ContactRepository();

    private ContactRepository() {
        contactsList = new HashMap<>();
        contactsList.put("JohnDoe@email.com", "John Doe");
        contactsList.put("SallySmith@email.com", "Sally Smith");
    }

    public void createContact(Contact contact) {
        contactsList.put(contact.getEmail(), contact.getName());
    }

    public void deleteContact(String email) {
        contactsList.remove(email);
    }

    public String findContactByEmail(String email) {
        return contactsList.get(email);
    }

    public boolean isContactExists(String email) {
        return contactsList.containsKey(email);
    }

    public boolean isEmpty() {
        return contactsList.isEmpty();
    }
}
