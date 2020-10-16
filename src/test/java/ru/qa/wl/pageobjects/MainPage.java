package ru.qa.wl.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

import static ru.qa.wl.tests.TestBase.app;

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



public MainPage(WebDriver wd) {
  PageFactory.initElements(wd, this);
  this.wd = wd;
}


  public List<String> getWhoWeServeList() {
  List<String> whoWeServeTitles = new ArrayList<>();
    for (WebElement element:whoWeServe ) {
      whoWeServeTitles.add(element.getText());

    }
   return whoWeServeTitles;
  }

  public void closePopup() {
  try {
    locationPopupYesButton.click();
  }catch (Exception e){
    return;

  }
}



  public List<String> getSuggestionsForSearch(String searchCriteria) throws InterruptedException {
    List<String> suggestion = new ArrayList<>();
    searchField.sendKeys(searchCriteria);
    Thread.sleep(500);
    for (WebElement element:suggestions) {
        suggestion.add(element.getText());
    }
  return suggestion;
  }

  public List<String> getResultForSearch(String searchCriteria) {
    List<String> titles = new ArrayList<>();
    searchField.sendKeys(searchCriteria);
    searchButton.click();
    for (WebElement title:productTitles) {
      titles.add(title.getText());
    }
   return titles;
  }
}
