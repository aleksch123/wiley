package ru.qa.wl.tests.ui;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.qa.wl.appmanager.ApplicationManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestBase {
    public static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    public static List<String> getDataFromFile(String fileName) throws IOException {
        List<String> titles = new ArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/" + fileName + ".csv")))) {
            String line = reader.readLine();
            titles = Arrays.asList(line.split(","));
        }
        return titles;
    }

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
        app.mainPage().closePopup();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }
}



