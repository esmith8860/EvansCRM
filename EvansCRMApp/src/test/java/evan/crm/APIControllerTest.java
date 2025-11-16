package evan.crm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APIControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "";
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
    }

    @Test
    public void testCreateContact() {
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
        String response = given()
                .contentType("application/json")
                .body("{\"name\":\"FetchMe\",\"email\":\"fetchme@email.com\"}")
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
                .body("{\"name\":\"FetchMe\",\"email\":\"fetchme@email.com\"}")
                .when()
                .delete("/api/evanscrm/contacts/" + id)
                .then()
                .statusCode(200)
                .body(org.hamcrest.Matchers.containsString("Contact deleted"));
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
