import BaseTest.AlertTest;
import Enums.AlertsButtons;
import PageObject.AlertPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static PageObject.AlertPage.ALERT_TEXT;
import static PageObject.AlertPage.CANCEL_TEXT;

public class clickBtnByJS extends AlertTest {


    @Override
    @BeforeClass
    public void setOn() {
        super.setOn();

    }


    @Test
    public void clickJsAlert (){
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.clickBtnByJs(AlertsButtons.ALERT);
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(true), ALERT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");


    }

    @Test
    public void disMissTest() {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.clickBtnByJs(AlertsButtons.CONFIRM);
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(false), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }
@Test
    public void confirmMiTest() {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.clickBtnByJs(AlertsButtons.CONFIRM);
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(true), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Ok");


    }
    int dataIndex = 0;

    @Test(dataProvider = "Providers")
    public void jsPrompt(boolean confirm, String input, String expected) {
        // Assuming driver and mainPage are initialized
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        // Loop through the data provider
        while (dataIndex < checkDataProvider().length) {
            try {

                AlertPage.clickBtnByJs(AlertsButtons.PROMPT);

                Thread.sleep(6000); // Wait for the alert to appear

                // Handle the alert
                ((JavascriptExecutor) driver).executeScript("arguments[0].sendKeys(arguments[0]);",
                        input);
                Thread.sleep(6000); // Wait for user action

                if (confirm) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].accept();");
                } else {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].dismiss();");
                }

                Thread.sleep(6000); // Wait for alert to close

                // Increment index and get new input
                dataIndex++;
                if (dataIndex < checkDataProvider().length) {
                    input = (String) checkDataProvider()[dataIndex][1];
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



        @DataProvider(name = "Providers")
        Object[][] checkDataProvider () {
            return new Object[][]{
                    {true, "Hello world", "You entered:Hello world"},
                    {false, "Hello world", "You entered:" },
                    {true, "", "You entered:"},
                    {false, "", "You entered:" }
            };

        }
}

