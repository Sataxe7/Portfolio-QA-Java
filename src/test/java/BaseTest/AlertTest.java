package BaseTest;

import Enums.AlertsButtons;
import PageObject.AlertPage;
import PageObject.MainPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static PageObject.AlertPage.ALERT_TEXT;
import static PageObject.AlertPage.CANCEL_TEXT;

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
    public void confirmDismissTest() {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();
        alertPage.clickOnButt(AlertsButtons.CONFIRM.getTEXT_ON_BUTTON());
        Assert.assertEquals(alertPage.switchToAlertAndGetText(false), CANCEL_TEXT);
        Assert.assertEquals(alertPage.getResultText(), "You clicked: Cancel");
    }


    private int dataIndex = 0; // Индекс для отслеживания текущего набора данных

    @Test(dataProvider = "Providers")

    public void processDataProvider(boolean confirm, String input, String expectedOutput) throws InterruptedException {
        mainPage.scrollToFooter(mainPage.btnForLink("javascript_alerts"));
        mainPage.clickOnAlert();
        Thread.sleep(2000);
        while (dataIndex < checkDataProvider().length) {
            Thread.sleep(2000);
            // Выполняем действия с алертом
            alertPage.clickOnButt(AlertsButtons.PROMPT.getTEXT_ON_BUTTON());
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(input);
            if (confirm) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            Thread.sleep(2000);
            // Получаем объект алерта

            Thread.sleep(2000);
            // Закрываем алерт
            dataIndex++;

            if (dataIndex < checkDataProvider().length) {
                input = (String) checkDataProvider()[dataIndex][1]; // Обновляем переменную input для следующей итерации
            }

        }


            }




            @DataProvider(name = "Providers")
            Object[][] checkDataProvider () {
                return new Object[][]{
                        {true, "Hello world", "You entered:Hello world"},
                        {false, "Hello world", "You entered:" + (null != null ? null : "")},
                        {true, "", "You entered:"},
                        {false, "", "You entered:" + (null != null ? null : "")}
                };

            }
        }

