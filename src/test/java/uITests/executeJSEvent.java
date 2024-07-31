package uITests;

import Enums.AlertsButtons;
import PageObject.AlertPage;
import driverManager.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static PageObject.AlertPage.*;
import static ui.LoginPasswordPage.EXPECTEDURL;

public class executeJSEvent extends AlertTest {
    @Test
    public void alertTest() throws InterruptedException {
        // Устанавливаем WebDriverWait с таймаутом в 10 секунд
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Прокручиваем страницу до элемента футера и кликаем по ссылке на страницу с алертами
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.btnForLink("javascript_alerts"))).click();

        // Переходим на страницу с алертами и нажимаем кнопку, вызывающую алерт
        alertPage.clickBtnByJs(AlertsButtons.ALERT);

        // Сравниваем текст алерта с ожидаемым
        Assert.assertEquals(alertPage.switchToAlertAndGetText(true), ALERT_TEXT);

        // Проверяем, что результат действия на странице соответствует ожиданиям
        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");
    }

    @Test
    public void confirmDismissTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.executeJsEvent(AlertsButtons.CONFIRM);
        Assert.assertEquals(alertPage.switchToAlertAndGetText(false), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }
    @Test
    public void confirmAlert() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.executeJsEvent(AlertsButtons.CONFIRM);
        Assert.assertEquals(alertPage.switchToAlertAndGetText(true),CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Ok");
    }


    int dataindex = 0;

    @Test(dataProvider = "Providers")
    public void processDataProvider(boolean confirm, String input, String expectedOutput) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.executeJsEvent(AlertsButtons.PROMPT);
        Assert.assertEquals(alertPage.switchToAlertAndGetText(confirm, input),PROMT_TEXT);
        Assert.assertEquals(alertPage.getResultText(), expectedOutput);

    }

    @DataProvider(name = "Providers")
    public Object[][] provideData() {
        return new Object[][]{
                {true, "Hello world", "You entered: Hello world"},
                {false, "Hello world", "You entered: null"},
                {true, "","You entered:"},
                {false, "You entered", "You entered: null"}
        };

    }

    @AfterMethod
    public void backdriver () {
        DriverManager.getDriver().navigate().back();
    }
}
