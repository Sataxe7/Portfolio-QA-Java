package uITests;

import driverManager.Listener;
import driverManager.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(Listener.class)
public class BaseTest {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUpClass() {
        driver = WebDriverFactory.initDriver();
    }

    public void openUrl(String expectedUrl) {
        driver.get(expectedUrl);
    }

    @AfterClass
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}