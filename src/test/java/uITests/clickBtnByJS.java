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
    MainPage mainPage;
    AlertPage alertPage;
    private static boolean isDataProviderTest;
    @BeforeMethod
    public void setOn() {
        openUrl("https://the-internet.herokuapp.com/");
        mainPage = new MainPage(driver);
        alertPage = new AlertPage(driver);
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
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(true), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Ok");
    }

    @Test(dataProvider = "Providers")
    public void jsPrompt(boolean confirm, String input, String expected) throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.clickBtnByJs(AlertsButtons.PROMPT);
        Assert.assertEquals(alertPage.switchToAlertAndGetText(confirm, input), PROMT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), expected);
        isDataProviderTest = true;
    }
    
    @DataProvider(name = "Providers")
    public Object[][] provideData() {
        return new Object[][]{
                {true, "Some text", "You entered: Some text"},
                {true, "Hello world", "You entered: Hello world"},
                {false, "Hello world", "You entered: null"},
                {true, "", "You entered:"},
                {false, "You entered", "You entered: null"}
        };
    }

    @AfterMethod
    public void backdriver() {
        if (isDataProviderTest) {
            driver.navigate().back();
            // Сброс флага после выполнения
            isDataProviderTest = false;
        }
    }
}


