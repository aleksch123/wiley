package ru.qa.wl.tests.ui;

import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UITests extends TestBase {
    @Features("UITest")
    @Stories("Comparison of page titles and template list")
    @Test
    public void titlesTest() throws IOException {

        List<String> titleList = app.mainPage().getWhoWeServeList();
        List<String> controlList = getDataFromFile("titles");
        assertEquals(titleList, controlList);
    }

    @Features("UITest")
    @Stories("Verify that suggestions meet search criteria ")
    @Test
    public void searchSuggestionTest() throws  InterruptedException {

        String searchCriteria = "Java";
        List<String> suggestions = app.mainPage().getSuggestionsForSearch(searchCriteria);
        suggestions.stream().forEach(v -> assertEquals(v,searchCriteria.toLowerCase()));

    }

    @Features("UITest")
    @Stories("Verify that book title contains search criteria and only 10 results are on the page ")
    @Test
    public void searchResultTest() throws InterruptedException {

        String searchCriteria = "Java";
        int index = 0;
        List<String> results = app.mainPage().getResultForSearch(searchCriteria);
        assertEquals(10, results.size());
        for (String bookTitle : results) {
            index++;
            assertTrue(bookTitle.contains(searchCriteria), "Expected word " + searchCriteria + " not found in title " + bookTitle);
            Map<String, ?> buttons = app.mainPage().getProductButton(index);
            Set keys = buttons.keySet();
            for (Iterator i = keys.iterator(); i.hasNext(); ) {
                String key = (String) i.next();
                if ((key.equals("EBook") || key.equals("Print")) && !buttons.get(key).equals("PRODUCTNOTAVAILABLEFORPURCHASE")) {
                    assertEquals("ADDTOCART", buttons.get(key));
                }
                if (key.equals("OBook")) {
                    assertEquals("VIEWONWILEYONLINELIBRARY", buttons.get(key));
                }
            }
        }

    }

    @Features("UITest")
    @Stories("Verify that header is presented and compare left panel items list with template list")
    @Test
    public void verifyEducationPage() throws IOException {

        app.goTo().education();
        assertTrue(app.educationPage().verifyHeader(), "Header expected isn't shown on the page");
        List<String> panelItems = app.educationPage().getLeftPanelItems();
        List<String> controlList = getDataFromFile("panelItems");
        assertEquals(controlList, panelItems);
    }
}
