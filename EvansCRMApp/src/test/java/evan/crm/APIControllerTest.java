package evan.crm;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import static io.restassured.RestAssured.*;

public class APIControllerTest {

    @Test
    public void testCRMAPI() {
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

        given()
            .when()
            .get("http://localhost:8080/api/evanscrm")
            .then()
            .statusCode(200)
            .assertThat()
            .body(org.hamcrest.Matchers.equalTo("API Test Successful"));
    }

    @Test
    public void testContactAPI() {
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());
        given()
            .when()
            .get("http://localhost:8080/api/evanscrm/contact?email=testname@email.com&name=TestName")
            .then()
            .statusCode(200)
            .assertThat()
            .body(org.hamcrest.Matchers.equalTo("Contact created: Contact(email=testname@email.com, name=TestName)"));
    }
}
