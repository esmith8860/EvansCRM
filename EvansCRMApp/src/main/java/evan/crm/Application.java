package evan.crm;
import evan.crm.dto.Contact;
import evan.crm.services.ContactService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ContactService contactService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Contact contact = new Contact("Evan Smith", "evansemail@email.com");
        contactService.createContact(contact);
    }
}
