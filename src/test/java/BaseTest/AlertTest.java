package BaseTest;

import Enums.AlertsButtons;
import PageObject.AlertPage;
import PageObject.MainPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
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
        Object[][] dataProvider = checkDataProvider();
        int dataProviderLength = dataProvider.length;
        while (dataIndex < dataProviderLength) {
            // Получаем текущий набор данных из провайдера
            boolean currentConfirm = (boolean) dataProvider[dataIndex][0];
            String currentInput = (String) dataProvider[dataIndex][1];
            String currentExpectedOutput = (String) dataProvider[dataIndex][2];

            // Выполняем действия с алертом
            alertPage.clickOnButt(AlertsButtons.PROMPT.getTEXT_ON_BUTTON());
            Thread.sleep(2000);
            alertPage.switchToAlertAndGetText(currentConfirm, currentInput); // Используем текущие значения input и confirm
            Thread.sleep(2000);
            Assert.assertEquals(alertPage.getResultText(), currentExpectedOutput); // Используем текущее ожидаемое значение
            Thread.sleep(2000);

            // Закрываем алерт
            dataIndex++;

            }

        }




            @DataProvider(name = "Providers")
            Object[][] checkDataProvider () {
                return new Object[][]{
                        {true, "Hello world", "You entered: Hello world"},
                        {false, "Hello world", "You entered:"},
                        {true, "", "You entered:"},
                        {false, "", "You entered:null"}
                };

            }
        }

