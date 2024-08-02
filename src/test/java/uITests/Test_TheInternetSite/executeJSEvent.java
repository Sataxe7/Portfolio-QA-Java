package uITests.Test_TheInternetSite;

import Enums.AlertsButtons;
import ui.TheInternet.AlertPage;
import driverManager.Listener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import driverManager.BaseTest;
import ui.TheInternet.MainPage;

import java.time.Duration;

import static ui.TheInternet.AlertPage.*;

@Listeners(Listener.class)
public class    executeJSEvent extends BaseTest {
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
        // Устанавливаем WebDriverWait с таймаутом в 10 секунд

        // Прокручиваем страницу до элемента футера и кликаем по ссылке на страницу с алертами
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
    mainPage.clickOnAlertJsExecutor("javascript_alerts");
        // Переходим на страницу с алертами и нажимаем кнопку, вызывающую алерт
        alertPage.clickBtnByJs(AlertsButtons.ALERT);

        // Сравниваем текст алерта с ожидаемым
        Assert.assertEquals(alertPage.switchToAlertAndGetText(true), ALERT_TEXT);

        // Проверяем, что результат действия на странице соответствует ожиданиям
        Assert.assertEquals(alertPage.getResultText(), "You successfully clicked an alert");
    }

    @Test
    public void confirmDismissTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.executeJsEvent(AlertsButtons.CONFIRM);
        Assert.assertEquals(alertPage.switchToAlertAndGetText(false), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }
    @Test
    public void confirmAlert() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlertJsExecutor("javascript_alerts");
        AlertPage.executeJsEvent(AlertsButtons.CONFIRM);
        Assert.assertEquals(alertPage.switchToAlertAndGetText(true),CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Ok");
    }



    @Test(dataProvider = "Providers")
    public void processDataProvider(boolean confirm, String input, String expectedOutput) throws InterruptedException {
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
    public void afterMethod(ITestResult result) {
        if (result.getMethod().getMethodName().contains("processDataProvider")) {
            driver.navigate().back();
        }
    }
}





