package ru.qa.wl.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static ru.qa.wl.tests.TestBase.app;

public class NavigationHelper {
    protected WebDriver wd;

    public NavigationHelper(WebDriver wd){ this.wd=wd; }


    public void education() {

        wd.get(app.properties.getProperty("web.baseUrl")+app.properties.getProperty("page.education"));

    }
}
