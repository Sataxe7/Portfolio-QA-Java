package uITests;

import Enums.AlertsButtons;
import PageObject.AlertPage;
import ui.hilel_site_obj.MainPage;
import driverManager.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static PageObject.AlertPage.*;

public class AlertTest extends BaseTest {

    protected MainPage mainPage;
    protected AlertPage alertPage;
    private WebDriverWait wait;

    @BeforeClass
    public void setOn() {
        setUpBrowser();
        openUrl("https://the-internet.herokuapp.com/");
        mainPage = new MainPage(driver);
        alertPage = new AlertPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Initialize WebDriverWait with 10 seconds timeout
    }

    @Test
    public void alertTest() throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Click for JS Alert']")));  // Add wait
        alertPage.clickOnBtn(AlertsButtons.ALERT.getTEXT_ON_BUTTON());

        Assert.assertEquals(alertPage.switchToAlertAndGetText(true), ALERT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");
    }

    @Test
    public void confirmDismissTest() throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Click for JS Confirm']")));  // Add wait
        alertPage.clickOnBtn(AlertsButtons.CONFIRM.getTEXT_ON_BUTTON());

        Assert.assertEquals(alertPage.switchToAlertAndGetText(false), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }

    @Test(dataProvider = "Providers")
    public void processDataProvider(boolean confirm, String input, String expectedOutput) throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Click for JS Prompt']")));  // Add wait
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
    public void backdriver() {
        DriverManager.getDriver().navigate().back();
    }
}