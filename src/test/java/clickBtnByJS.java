import uITests.AlertTest;
import Enums.AlertsButtons;
import PageObject.AlertPage;
import driverManager.DriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static PageObject.AlertPage.*;

public class clickBtnByJS extends AlertTest {


    @Override
    @BeforeClass
    public void setOn() {
        super.setOn();

    }


    @Test
    public void clickJsAlert () throws InterruptedException {
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

    @Test(dataProvider = "Providersd")
    public void jsPrompt(boolean confirm, String input, String expected) throws InterruptedException {
        // Assuming driver and mainPage are initialized
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        // Loop through the data provider
                AlertPage.clickBtnByJs(AlertsButtons.PROMPT);
        Assert.assertEquals(alertPage.switchToAlertAndGetText(confirm, input),PROMT_TEXT);
                // Increment index and get new input
        Assert.assertEquals(alertPage.getResultText(), expected);
            }



        @DataProvider(name = "Providersd")
        Object[][] checkDataProvider () {
            return new Object[][]{
                    {true, "Hello world", "You entered: Hello world"},
                    {false, "Hello world", "You entered: null"},
                    {true, "", "You entered:"},
                    {false, "", "You entered: null" }
            };

        }
    @AfterMethod
    public void backdriver () {
        DriverManager.getDriver().navigate().back();
    }
}






