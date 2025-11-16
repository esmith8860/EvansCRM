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

    @Getter
    private static final ContactRepository instance = new ContactRepository();

    private ContactRepository() {
        contactsList = new HashMap<>();
    }

    public UUID createContact(Contact contact) {
        UUID id = UUID.randomUUID();
        contact.setId(id);
        contactsList.put(id, contact);
        return id;
    }

    public boolean deleteContact(UUID id) {
        contactsList.remove(id);
        return !isContactExists(id);
    }

    public Contact findContactById(UUID id) {
        return contactsList.get(id);
    }

    public boolean isContactExists(UUID id) {
        return contactsList.containsKey(id);
    }

    public boolean isEmpty() {
        return contactsList.isEmpty();
    }

    public String getContactsList() {
        return contactsList.toString();
    }

    public boolean updateContact(Contact contactToUpdate) {
        Contact contact = contactsList.get(contactToUpdate.getId());

        if (contact == null) {
            return false;
        }

        contact.setName(contact.getName());
        contact.setEmail(contact.getEmail());
        return true;
    }
}
