package PageObject;

import Enums.AlertsButtons;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertPage extends BasePage {
    public final static String ALERT_TEXT = "I am a JS Alert";
    public final static String CANCEL_TEXT = "I am a JS Confirm";
    public final static String PROMT_TEXT = "I am a JS prompt";
    private WebDriverWait wait;

    public AlertPage(WebDriver driver) {

        super(driver);
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    }

    private static By alertsBtn(String nameButton) {
        return By.xpath("//button[contains(text(), '" + nameButton + "')]");

    }

    private By resultOfText() {
        return By.cssSelector("#result");
    }

    public void clickOnButt(String nameButton) {
        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.presenceOfElementLocated(alertsBtn(nameButton)));
        element.click();

    }

    public static void clickBtnByJs(AlertsButtons button) {
        WebElement buttonToClick = driver.findElement(alertsBtn(button.getTEXT_ON_BUTTON()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return arguments[0].click()", buttonToClick);
    }

    public void executeJsScript(AlertsButtons button) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return " + button.getON_CLICK_JS_FUCTION());
    }

    public String switchToAlertAndGetText(boolean confirm, String... message) {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (message.length > 0) {
            alert.sendKeys(message[0]);
        }
        if (confirm) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return alertText;
    }

    public String switchToAlertAndGetTextByJs(boolean confirm, String... message) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String alertText = "";

        try {
            // Получаем текущий алерт
            Alert alert = driver.switchTo().alert();

            // Получаем текст алерта
            alertText = alert.getText();

            // Отправляем сообщение, если оно есть
            if (message.length > 0) {
                js.executeScript("arguments[0].value = '" + message[0] + "';", alert);
            }

            // Принимаем или отклоняем алерт в зависимости от параметра confirm
            if (confirm) {
                alert.accept();
            } else {
                alert.dismiss();
            }

        } catch (NoAlertPresentException e) {
            System.out.println("Алерт не найден");
        }

        return alertText;
    }

public String  getResultText() {
        return driver.findElement(resultOfText()).getText();

    }
    }
