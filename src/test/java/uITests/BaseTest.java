package uITests;

import driverManager.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUpBrowser() {
        driver = WebDriverFactory.initDriver();
    }

    public static void openUrl(String expectedUrl) {
        driver.get(expectedUrl);
    }

    @AfterClass
    public static void tearDown() {
       WebDriverFactory.quitDriver();
    }
}