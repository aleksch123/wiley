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

public class ApiTests {
    @Features("ApiTests")
    @Stories("Verify response values")
    @Test
    public void getFieldInResponseWithValueAssertion() {
        Response response = given().log().uri()
                .when().get("https://www.wiley.com/en-us/search/autocomplete/comp_00001H9J?term=Java")
                .then()
                .statusCode(200)
                .extract().response();
        List<String> suggestions = response.path("suggestions.findAll {it.term.contains('java')}");
        Assert.assertEquals(4, suggestions.size());
        List<String> titles = response.path("pages.findAll {it.title.contains('Wiley')}");
        Assert.assertEquals(4, titles.size());

    }
    @Features("ApiTests")
    @Stories("Verify json schema")
    @Test
    public void validateJsonSchema() {

        given().log().uri()
                .when().get("https://httpbin.org/delay/5")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("jsonSchema.json")).log().body();
    }
    @Features("ApiTests")
    @Stories("Verify response content type")
    @Test
    public void validateImageContent() {

        given().log().uri()
                .when().get("https://httpbin.org/image/png")
                .then()
                .statusCode(200)
                .header("content-type", "image/png")
                .header("content-length", notNullValue());
    }
}
