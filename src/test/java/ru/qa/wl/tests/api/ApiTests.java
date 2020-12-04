package ru.qa.wl.tests.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.List;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.qa.wl.tests.api.Constants.Servers.*;

public class ApiTests {
    @Features("ApiTests")
    @Stories("Verify response values")
    @Test
    public void getFieldInResponseWithValueAssertion() {

        Response response = given().log().uri()
                .when().get(SEARCH_URL+ "Java" )
                .then()
                .statusCode(200)
                .extract().response();

        List<String> suggestions = response.path("suggestions.findAll {it.term.contains('java')}");
        Assert.assertEquals( suggestions.size(),4);
        List<String> titles = response.path("pages.findAll {it.title.contains('Wiley')}");
        Assert.assertEquals( titles.size(),4);

    }
    @Features("ApiTests")
    @Stories("Verify json schema")
    @Test
    public void validateJsonSchema() {

        given().log().uri()
                .when().get(DELAY_URL)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonSchema.json")).log().body();
    }
    @Features("ApiTests")
    @Stories("Verify response content type")
    @Test
    public void validateImageContent() {

        given().log().uri()
                .when().get(IMAGE_URL)
                .then()
                .statusCode(200)
                .header("content-type", "image/png")
                .header("content-length", notNullValue());
    }
}
