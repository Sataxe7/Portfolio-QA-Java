package driverManager;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static driverManager.SetUp.setUp;
public class BaseTestHillel {
    @BeforeClass
    public void setpBrowser() {
        setUp();
    }


    @AfterMethod
    public void closeBrowser() {
        closeWebDriver();
    }

}
