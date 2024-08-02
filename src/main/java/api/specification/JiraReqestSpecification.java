package api.specification;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class JiraReqestSpecification {

    public static RequestSpecification reqestSpec =
            new RequestSpecBuilder()
                    .setBaseUri("https://jira.ithillel.com")
                    .setContentType(ContentType.JSON)
                    .setAccept("application/json")
                    .setAuth(RestAssured.preemptive().basic("Trofan", "guerilla12"))
                    .addHeader("User-Agent", "hillel_automationQA")
                    .log(LogDetail.ALL)
                    .build()
                    .filter(new AllureRestAssured());
}
