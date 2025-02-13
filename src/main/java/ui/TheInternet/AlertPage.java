package ui.TheInternet;

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private static By alertsBtn(String nameButton) {
        return By.xpath("//button[contains(text(), '" + nameButton + "')]");

}

private By resultOfText() {
    return By.cssSelector("#result");
}

public void clickOnBtn(String nameButton) {
    WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(20)))
            .until(ExpectedConditions.presenceOfElementLocated(alertsBtn(nameButton)));
    element.click();

}

    public static void clickBtnByJs(AlertsButtons button) {
        WebElement buttonToClick = driver.findElement(alertsBtn(button.getTEXT_ON_BUTTON()));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return arguments[0].click()", buttonToClick);
    }

    public static void executeJsEvent(AlertsButtons button) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return " + button.getON_CLICK_JS_FUCTION());
    }


    public String switchToAlertAndGetText(boolean confirm, String... message) throws InterruptedException {
        Alert alert = driver.switchTo().alert();
        Thread.sleep(3000);
        String alertText = alert.getText();
        if (message.length > 0) {
            alert.sendKeys(message[0]);
        }
        if (confirm) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        // Получаем текст алерта после взаимодействия с ним
        return alertText;
    }


    public String switchToAlertAndGetTextByJs(boolean confirm, String... message) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Получаем текущий алерт
        Alert alert = driver.switchTo().alert();
        Thread.sleep(3000);
        // Получаем текст алерта
        String alertText = alert.getText();
        // Отправляем сообщение, если оно есть
        if (message.length > 0) {
            // Execute JavaScript to set the value of the alert
            js.executeScript("arguments[0].innerText = '" + message[0] + "'", alert);
        }

        if (confirm) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        return alertText;
    }

        public String getResultText () {
            return driver.findElement(resultOfText()).getText();

        }
    }
