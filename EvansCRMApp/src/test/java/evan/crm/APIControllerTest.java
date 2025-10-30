package evan.crm;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import static io.restassured.RestAssured.*;

public class APIControllerTest {

    @Test
    public void testAPI() {
        RestAssured.config = RestAssured.config().sslConfig(SSLConfig.sslConfig().relaxedHTTPSValidation());

        given()
            .when()
            .get("http://localhost:8080/api/evanscrm")
            .then()
            .statusCode(200)
            .assertThat()
            .body(org.hamcrest.Matchers.equalTo("API Test Successful"));
    }
}
