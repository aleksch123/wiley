package ru.qa.wl.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPage {


    protected WebDriver wd;

    @FindBy(xpath = "//a[@class='who-we-serve-block-title']")
    private List<WebElement> whoWeServe;

    @FindBy(xpath = "//button[contains(.,'YES')]")
    private WebElement locationPopupYesButton;

    @FindBy(xpath = "//input[@id='js-site-search-input']")
    private WebElement searchField;

    @FindBy(xpath = "//span[@class='search-highlight']")
    private List<WebElement> suggestions;


    @FindBy(xpath = "//button[@type='submit']")
    private WebElement searchButton;


    @FindBy(xpath = "//h3[@class='product-title']")
    private List<WebElement> productTitles;


    @FindBy(xpath = "//section[@class='product-item'][1]//span[@class='product-type-name']")
    private List<WebElement> productTabs;

    @FindBy(xpath = "//span[@class='product-type-name']/following::form//span")
    private List<WebElement> searchContent;


    public MainPage(WebDriver wd) {
        PageFactory.initElements(wd, this);
        this.wd = wd;
    }

    public List<String> getWhoWeServeList() {
        List<String> whoWeServeTitles = new ArrayList<>();
        for (WebElement element : whoWeServe) {
            whoWeServeTitles.add(element.getText());

        }
        return whoWeServeTitles;
    }

    public void closePopup() {
        try {
            locationPopupYesButton.click();
        } catch (Exception e) {
            return;

        }
    }


    public List<String> getSuggestionsForSearch(String searchCriteria) throws InterruptedException {
        List<String> suggestion = new ArrayList<>();
        searchField.sendKeys(searchCriteria);
        Thread.sleep(500);
        for (WebElement element : suggestions) {
            suggestion.add(element.getText());
        }
        return suggestion;
    }

    public List<String> getResultForSearch(String searchCriteria) throws InterruptedException {
        List<String> titles = new ArrayList<>();
        searchField.sendKeys(searchCriteria);
        Thread.sleep(500);
        searchButton.click();
        for (WebElement title : productTitles) {
            titles.add(title.getText());
        }
        return titles;
    }

    public Map<String, ?> getProductButton(int itemNum) {
        Map captions = new HashMap();
        String caption = "";
        String key = "";

        List<WebElement> idList = wd.findElements(By.xpath("//section[@class='product-item'][" + itemNum + "]//div[contains(@class,'tab-pane')]"));
        for (WebElement element : idList) {
            String id = element.getAttribute("id");
            if (id.contains("E-Book") || id.contains("Print")) {
                try {
                    caption = wd.findElement(By.xpath("//div[@id='" + id + "']//button[@class='small-button add-to-cart-button js-add-to-cart']"))
                            .getAttribute("innerText")
                            .toUpperCase()
                            .replaceAll("[^a-zA-Z]", "");
                } catch (Exception e) {
                    caption = "N/A";
                }
            } else if (id.contains("O-Book")) {
                try {
                    caption = wd.findElement(By.xpath("//div[@id='" + id + "']//a[@class='small-button learn-more-button']"))
                            .getAttribute("innerText")
                            .replaceAll("[^a-zA-Z]", "");
                } catch (Exception e) {
                    caption = "N/A";
                }
            }
            key = id.replaceAll("[^a-zA-Z]", "");
            captions.put(key, caption);
        }

        return captions;
    }
}
