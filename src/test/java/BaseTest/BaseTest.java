package BaseTest;

import driverManager.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

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
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}