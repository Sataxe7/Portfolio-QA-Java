package ui.Test_TheInternetSite;

import Enums.AlertsButtons;
import ui.TheInternet.AlertPage;
import org.testng.ITestResult;
import org.testng.annotations.*;
import driverManager.BaseTest;
import ui.TheInternet.MainPage;
import org.testng.Assert;

import static ui.TheInternet.AlertPage.*;


public class AlertTest extends BaseTest {

    MainPage mainPage;
    AlertPage alertPage;



    @BeforeMethod
    public void setOn() {
        openUrl("https://the-internet.herokuapp.com/");
        mainPage = new MainPage(driver);
        alertPage = new AlertPage(driver);
    }

    @Test
    public void alertTest() throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();
        alertPage.clickOnBtn(AlertsButtons.ALERT.getTEXT_ON_BUTTON());
        Assert.assertEquals(alertPage.switchToAlertAndGetText(true), ALERT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");
    }

    @Test
    public void confirmDismissTest() throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();
        alertPage.clickOnBtn(AlertsButtons.CONFIRM.getTEXT_ON_BUTTON());
        Assert.assertEquals(alertPage.switchToAlertAndGetText(false), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }

    @Test(dataProvider = "Providers")
    public void processDataProvider(boolean confirm, String input, String expectedOutput) throws InterruptedException {

        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();
        alertPage.clickOnBtn(AlertsButtons.PROMPT.getTEXT_ON_BUTTON());
        Assert.assertEquals(alertPage.switchToAlertAndGetText(confirm, input), PROMT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), expectedOutput);


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
        if (result.getMethod().getMethodName().equals("processDataProvider")) {
            driver.navigate().back(); // Возврат на предыдущую страницу только для метода processDataProvider
        }

        // Закрытие драйвера после каждого теста

        }
    }


