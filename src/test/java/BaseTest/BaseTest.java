package BaseTest;

import PageObject.MainPage;
import WebDriverManager.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverBrowser;
import org.openqa.selenium.WebDriver;


import PageObject.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class BaseTest  {
    protected WebDriver driver;


    @BeforeTest
    public void SetUpBrowser() {
       driver=DriverManager.getDriver();
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