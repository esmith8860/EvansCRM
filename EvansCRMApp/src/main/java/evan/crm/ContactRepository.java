package evan.crm;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public class ContactRepository {
    private final HashMap<UUID, Contact> contactsList;
    //private UUID uuid;

    @Getter
    private static final ContactRepository instance = new ContactRepository();

    private ContactRepository() {
        contactsList = new HashMap<>();
        UUID uuid = UUID.fromString("6fcdcba5-483e-4489-8dcb-a5483ec489bb");
        Contact contact = new Contact("John Doe", "JohnDoe@email.com");
        contactsList.put(uuid, contact);
    }

    public UUID createContact(Contact contact) {
        UUID uuid = UUID.randomUUID();
        contactsList.put(uuid, contact);
        return uuid;
    }

    public boolean deleteContact(UUID uuid) {
        contactsList.remove(uuid);
        return !isContactExists(uuid);
    }

    public Contact findContactByUUID(UUID uuid) {
        return contactsList.get(uuid);
    }

    public boolean isContactExists(UUID uuid) {
        return contactsList.containsKey(uuid);
    }

    public boolean isEmpty() {
        return contactsList.isEmpty();
    }
}
