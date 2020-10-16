package ru.qa.wl.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.qa.wl.pageobjects.EducationPage;
import ru.qa.wl.pageobjects.MainPage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public final Properties properties;
    public Map<String, Object> vars;
    WebDriver wd;
    private final String browser;
    private MainPage mainPage;
    private EducationPage educationPage;
    private NavigationHelper navigationHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {
        wd = new ChromeDriver();
        properties.load(new FileReader(new File("src/test/resources/test.properties")));
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));
        wd.manage().window().maximize();
        mainPage = new MainPage(wd);
        educationPage = new EducationPage(wd);
        navigationHelper = new NavigationHelper(wd);

    }

    public void stop() {
        wd.quit();
    }

    public MainPage mainPage() {
        return mainPage;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public EducationPage educationPage() {
        return educationPage;
    }

}
