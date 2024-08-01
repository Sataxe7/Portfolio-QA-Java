package Api.jira;

import api.pojo.IssueType;
import api.pojo.Project;
import api.pojo.body_request.JiraIssueRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static api.constnts.ValueName.ISSUE_TYP_TASK;
import static api.constnts.ValueName.PROJECT_ID;
import static api.request.SendRequest.sendPostRequest;
import static api.specification.JiraReqestSpecification.reqestSpec;
import static org.hamcrest.Matchers.containsString;

public class TestCreateIssueWthisPOJO {


    @Test
    public void testCreatIssueWthisPOJO() {
        JiraIssueRequest issueRequest = new JiraIssueRequest(
                new Project(PROJECT_ID),
                "This is new task POJO.",
                "Creating of an issue using project keys and issue type names using the REST API",
                new IssueType(ISSUE_TYP_TASK)
        );

        Response response = (Response) sendPostRequest(
                reqestSpec,
                issueRequest,
                "/rest/api/2/issue/",
        HttpStatus.SC_CREATED);

        response.then()
                .body("key", containsString(PROJECT_ID));

    }
}
