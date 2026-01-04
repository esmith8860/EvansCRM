package evan.crm.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

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
                .post("/api/evanscrm/contacts")
                .then()
                .statusCode(200)
                .assertThat()
                .body(org.hamcrest.Matchers.containsString("testname@email.com"));
    }

    @Test
    public void testGetContactById() {
        String id = given()
                .contentType("application/json")
                .body("{\"name\":\"FetchMe\",\"email\":\"fetchme@email.com\"}")
                .when()
                .post("/api/evanscrm/contacts")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

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
        String id = given()
                .contentType("application/json")
                .body("{\"name\":\"UpdateMe\",\"email\":\"updateme@email.com\"}")
                .when()
                .post("/api/evanscrm/contacts")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        given()
                .contentType("application/json")
                .body("{\"name\":\"UpdatedName\",\"email\":\"updatedname@email.com\"}")
                .when()
                .put("/api/evanscrm/contacts/" + id)
                .then()
                .statusCode(200)
                .body(org.hamcrest.Matchers.containsString(id));
    }

    @Test
    public void testDeleteContact() {
        String id = given()
                .contentType("application/json")
                .body("{\"name\":\"DeleteMe\",\"email\":\"deleteme@email.com\"}")
                .when()
                .post("/api/evanscrm/contacts")
                .then()
                .statusCode(200)
                .extract()
                .path("id");

        given()
                .contentType("application/json")
                .body("{\"name\":\"DeleteMe\",\"email\":\"deleteme@email.com\"}")
                .when()
                .delete("/api/evanscrm/contacts/" + id)
                .then()
                .statusCode(200)
                .body(org.hamcrest.Matchers.containsString(id));
    }
}
