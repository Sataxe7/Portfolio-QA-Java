package uITests;

import Enums.AlertsButtons;
import PageObject.AlertPage;
import driverManager.Listener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ui.hilel_site_obj.MainPage;
import driverManager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static PageObject.AlertPage.*;
import static driverManager.DriverManager.getDriver;

@Listeners(Listener.class)
public class AlertTest extends BaseTest {

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
        if (result.getMethod().getMethodName().equals("processDataProvider")) {
            driver.navigate().back(); // Возврат на предыдущую страницу только для метода processDataProvider
        }

        // Закрытие драйвера после каждого теста
        if (driver != null) {
            driver.quit();
        }
    }
}


