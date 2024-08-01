package uITests;

import driverManager.Listener;
import driverManager.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(Listener.class)
public class BaseTest {
    protected static WebDriver driver;

    @BeforeTest
    public static void setUpClass() {
        driver = WebDriverFactory.initDriver();
    }

    public void openUrl(String expectedUrl) {
        driver.get(expectedUrl);
    }

    @AfterTest
    public static void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }
}