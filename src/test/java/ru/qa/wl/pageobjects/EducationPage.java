package ru.qa.wl.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class EducationPage {

    protected WebDriver wd;

    @FindBy(xpath = "//span[contains(.,'Education')]")
    private WebElement header;

    @FindBy(xpath = "//div[@class='side-panel']//a[contains(@href, 'us/Education')]")
    private List<WebElement> panelItems;


    public EducationPage(WebDriver wd) {
        PageFactory.initElements(wd, this);
        this.wd = wd;
    }


    public boolean verifyHeader() {
        return header.isDisplayed();

    }

    public List<String> getLeftPanelItems() {

        List<String> items = new ArrayList<>();
        panelItems.stream().forEach(v ->items.add(v.getText()));
        return items;
    }
}
