package ui.Test_TheInternetSite;

import Enums.AlertsButtons;
import ui.TheInternet.AlertPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import driverManager.BaseTest;
import ui.TheInternet.MainPage;

import static ui.TheInternet.AlertPage.*;

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
    public void afterMethod(ITestResult result) {
        if (result.getMethod().getMethodName().contains("jsPrompt")) {
            driver.navigate().back();
            if (driver != null) {
                driver.quit();
            }

        }
    }

}

