package api.request;

import api.pojo.body_request.JiraIssueRequest;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.devtools.v118.network.model.Response;

import static io.restassured.RestAssured.given;

public class SendRequest {

    public static Response sendPostRequest(RequestSpecification recSpec, JiraIssueRequest jiraIssueRequest, String endpoint,
                                           int expectedStatusCod) {
        return (Response) given()
                .spec(recSpec)
                .when()
                .body(jiraIssueRequest)
                .log().all()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(expectedStatusCod)
                .extract().response();

    }
}