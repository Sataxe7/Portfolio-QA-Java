package BaseTest;

import Enums.AlertsButtons;
import PageObject.AlertPage;
import PageObject.MainPage;
import WebDriverManager.DriverManager;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static PageObject.AlertPage.*;

public class AlertTest extends BaseTest {

    protected MainPage mainPage;
    protected AlertPage alertPage;


    @BeforeClass
    public void setOn() {
        SetUpBrowser();
        openUrl("https://the-internet.herokuapp.com/");
        mainPage = new MainPage(driver);
        alertPage = new AlertPage(driver);
    }


    @Test
    public void alertTest() throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();
        alertPage.clickOnButt(AlertsButtons.ALERT.getTEXT_ON_BUTTON());
        Assert.assertEquals(alertPage.switchToAlertAndGetText(true), ALERT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");
    }

    @Test
    public void confirmDismissTest() throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();
        alertPage.clickOnButt(AlertsButtons.CONFIRM.getTEXT_ON_BUTTON());
        Assert.assertEquals(alertPage.switchToAlertAndGetText(false), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }


    int dataindex = 0;

    @Test(dataProvider = "Providers")
    public void processDataProvider(boolean confirm, String input, String expectedOutput) throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();
        Thread.sleep(4000);
        alertPage.clickOnButt(AlertsButtons.PROMPT.getTEXT_ON_BUTTON());
        Thread.sleep(4000);
        Alert alert = driver.switchTo().alert();
        String[] message = new String[0];
        if (message.length > 0) {
            alert.sendKeys(message[0]);
            alert.accept();
        }
       Assert.assertEquals(alert.accept),(input),expectedOutput),PROMT_TEXT);
        Thread.sleep(4000);
        Assert.assertEquals(alertPage.getResultText(), expectedOutput);
kkk
    }

    @DataProvider(name = "Providers")
    public Object[][] provideData() {
        return new Object[][]{
                {"Some text","You entered:"},
                {true, "Hello world", "You entered: Hello world"},
                {false, "Hello world", "You entered:null"},
                {true, "","You entered:"},
                {false, "You entered", "You entered: null"}
        };

    }

    @AfterMethod
        public void backdriver () {
            DriverManager.getDriver().navigate().back();
        }
    }








