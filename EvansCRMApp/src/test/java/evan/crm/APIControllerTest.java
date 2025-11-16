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
                .param("name", "TestName")
                .param("email", "testname@email.com")
                .when()
                .post("/api/evanscrm/contacts/create")
                .then()
                .statusCode(200)
                .assertThat()
                .body(org.hamcrest.Matchers.containsString("Contact created"));
    }
}
