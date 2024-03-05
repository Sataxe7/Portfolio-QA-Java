package BaseTest;

import driverManager.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;


    @BeforeTest
    public void setUpBrowser() {
        driver = WebDriverFactory.initDriver();
    }

    public void openUrl(String expectedUrl) {
        driver.get(expectedUrl);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}