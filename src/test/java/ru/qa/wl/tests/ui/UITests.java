package ru.qa.wl.tests.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;
import java.util.List;

public class UITests extends TestBase {
    @Features("UITest")
    @Stories("Comparison of page titles and template list")
    @Test
    public void titlesTest() throws IOException {

        List<String> titleList = app.mainPage().getWhoWeServeList();
        List<String> controlList = getDataFromFile("titles");
        Assert.assertEquals(titleList, controlList);
    }
    @Features("UITest")
    @Stories("Verify that suggestions meet search criteria ")
    @Test
    public void searchSuggestionTest() throws IOException, InterruptedException {

        String searchCriteria = "Java";
        List<String> suggestions = app.mainPage().getSuggestionsForSearch(searchCriteria);
        for (String item : suggestions) {
            Assert.assertEquals(item, searchCriteria.toLowerCase());
        }
    }
    @Features("UITest")
    @Stories("Verify that book title contains search criteria and only 10 results are on the page ")
    @Test
    public void searchResultTest() throws InterruptedException {

        String searchCriteria = "Java";
        List<String> results = app.mainPage().getResultForSearch(searchCriteria);
        Assert.assertEquals(10, results.size());
        for (String bookTitle : results) {
            Assert.assertTrue(bookTitle.contains(searchCriteria), "Expected word " + searchCriteria + " not found in title " + bookTitle);
        }
    }
    @Features("UITest")
    @Stories("Verify that header is presented and compare left panel items list with template list")
    @Test
    public void verifyEducationPage() throws IOException {

        app.goTo().education();
        Assert.assertTrue(app.educationPage().verifyHeader(), "Header expected isn't shown on the page");
        List<String> panelItems = app.educationPage().getLeftPanelItems();
        List<String> controlList = getDataFromFile("panelItems");
        Assert.assertEquals(controlList, panelItems);
    }
}
