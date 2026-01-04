package evan.crm.api;

import evan.crm.dto.Contact;
import evan.crm.services.ContactService;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APIControllerTestMockito {

    @LocalServerPort
    private int port;

    //@MockBean
    @MockitoBean
    private ContactService contactService;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "";
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
    }

    @Test
    public void testCreateContact() {
        Contact mockContact = new Contact("TestName", "testname@email.com");

        when(contactService.createContact(any(Contact.class))).thenReturn(mockContact);

        given()
                .contentType("application/json")
                .body("{\"name\":\"TestName\",\"email\":\"testname@email.com\"}")
                .when()
                .post("/api/evanscrm/contacts/create")
                .then()
                .statusCode(200)
                .assertThat()
                .body(org.hamcrest.Matchers.containsString("Contact created"));
    }

    @Test
    public void testGetContactById() {
        Contact mockContact = new Contact("FetchMe", "fetchme@email.com");
        UUID id = UUID.randomUUID();
        mockContact.setId(id);

        when(contactService.createContact(any(Contact.class))).thenReturn(mockContact);
        when(contactService.getContactById(id)).thenReturn(mockContact);

        String response = given()
                .contentType("application/json")
                .body("{\"name\":\"FetchMe\",\"email\":\"fetchme@email.com\"}")
                .when()
                .post("/api/evanscrm/contacts/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        given()
                .contentType("application/json")
                .body("{\"name\":\"FetchMe\",\"email\":\"fetchme@email.com\"}")
                .when()
                .get("/api/evanscrm/contacts/" + id)
                .then()
                .statusCode(200)
                .body(org.hamcrest.Matchers.containsString("{\"id\":\"" + id + "\""));
    }

    @Test
    public void testUpdateContact() {
        String response = given()
                .contentType("application/json")
                .body("{\"name\":\"UpdateMe\",\"email\":\"updateme@email.com\"}")
                .when()
                .post("/api/evanscrm/contacts/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        String id = response.substring(
                response.indexOf("id=") + 3,
                response.indexOf(",", response.indexOf("id="))
        );

        given()
                .contentType("application/json")
                .body("{\"name\":\"UpdatedName\",\"email\":\"updatedname@email.com\"}")
                .when()
                .put("/api/evanscrm/contacts/" + id)
                .then()
                .statusCode(200)
                .body(org.hamcrest.Matchers.containsString("Contact updated"));
    }

    @Test
    public void testDeleteContact() {
        String response = given()
                .contentType("application/json")
                .body("{\"name\":\"DeleteMe\",\"email\":\"deleteme@email.com\"}")
                .when()
                .post("/api/evanscrm/contacts/create")
                .then()
                .statusCode(200)
                .extract()
                .asString();

        String id = response.substring(
                response.indexOf("id=") + 3,
                response.indexOf(",", response.indexOf("id="))
        );

        given()
                .contentType("application/json")
                .body("{\"name\":\"DeleteMe\",\"email\":\"deleteme@email.com\"}")
                .when()
                .delete("/api/evanscrm/contacts/" + id)
                .then()
                .statusCode(200)
                .body(org.hamcrest.Matchers.containsString("Contact deleted"));
    }
}
