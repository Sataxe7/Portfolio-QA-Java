package Api.jira;

import driverManager.Listener;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class TestCreateIssue {

    @Test
    public void testCreateIssue() {
        RestAssured.baseURI = "https://jira.ithillel.com";

        String userName = "Trofan";
        String password = "guerilla12";

        String requestBady = "{\n" +
                "    \"fields\": {\n" +
                "       \"project\":\n" +
                "       {\n" +
                "          \"key\": \"ONQM0111\"\n" +
                "       },\n" +
                "       \"summary\": \"REST ye merry gentlemen.\",\n" +
                "       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\n" +
                "       \"issuetype\": {\n" +
                "          \"name\": \"Task\"\n" +
                "       }\n" +
                "   }\n" +
                "}";

        given()
                .auth()
                .preemptive()
                .basic(userName, password)
                .header("Content-Type", "application/json")
                .body(requestBady)
                .when()
                .post("/rest/api/2/issue/")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("key", containsString("ONQM0111"));
    }
}
