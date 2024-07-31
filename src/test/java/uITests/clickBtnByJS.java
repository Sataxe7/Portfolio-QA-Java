package uITests;

import Enums.AlertsButtons;
import PageObject.AlertPage;
import driverManager.DriverManager;
import driverManager.Listener;
import driverManager.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ui.hilel_site_obj.MainPage;

import java.time.Duration;

import static PageObject.AlertPage.*;
@Listeners(Listener.class)
public class clickBtnByJS extends BaseTest {

    private MainPage mainPage;
    private AlertPage alertPage;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        openUrl("https://the-internet.herokuapp.com/");
        mainPage = new MainPage(driver);
        alertPage = new AlertPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void clickJsAlert() throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.clickBtnByJs(AlertsButtons.ALERT);
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(true), ALERT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");
    }

    @Test
    public void disMissTest() throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.clickBtnByJs(AlertsButtons.CONFIRM);
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(false), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }

    @Test
    public void confirmMiTest() throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.clickBtnByJs(AlertsButtons.CONFIRM);
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(true), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Ok");
    }

    @Test(dataProvider = "Providersd")
    public void jsPrompt(boolean confirm, String input, String expected) throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.clickBtnByJs(AlertsButtons.PROMPT);
        Assert.assertEquals(alertPage.switchToAlertAndGetText(confirm, input), PROMT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), expected);
    }

    @DataProvider(name = "Providersd")
    Object[][] checkDataProvider() {
        return new Object[][]{
                {true, "Hello world", "You entered: Hello world"},
                {false, "Hello world", "You entered: null"},
                {true, "", "You entered:"},
                {false, "", "You entered: null"}
        };
    }

    @AfterMethod
    public void cleanUp(ITestResult result) {
        // Use this check to ensure `back()` is called only for data-driven tests
        if (result.getMethod().getMethodName().equals("jsPrompt")) {
            driver.navigate().back();
        }
    }
}



