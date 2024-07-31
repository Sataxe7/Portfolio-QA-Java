package uITests;

import Enums.AlertsButtons;
import PageObject.AlertPage;
import driverManager.DriverManager;
import driverManager.WebDriverFactory;
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

public class clickBtnByJS extends AlertTest {




    @Test
    public void clickJsAlert () throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.clickBtnByJs(AlertsButtons.ALERT);
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(true), ALERT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");


    }

    @Test
    public void disMissTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButtonId")));
        AlertPage.clickBtnByJs(AlertsButtons.CONFIRM);
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(false), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }
@Test
    public void confirmMiTest() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
    wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmButtonId")));
        AlertPage.clickBtnByJs(AlertsButtons.CONFIRM);
    wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alertPage.switchToAlertAndGetTextByJs(true), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Ok");


    }

    @Test(dataProvider = "Providersd")
    public void jsPrompt(boolean confirm, String input, String expected) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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






