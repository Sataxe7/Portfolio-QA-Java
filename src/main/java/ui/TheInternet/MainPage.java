package ui.TheInternet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {
    public static final String MAINPAGE="https://the-internet.herokuapp.com/";

    public MainPage(WebDriver driver) {
        super(driver);
    }
        public By btnForLink(String nameButton) {
            return By.xpath("//a[@href='/" + nameButton + "']");
        }
        private By footer() {
            return By.id("page-footer");
        }
        private  String buttonJavaScriptAlert() {
        return "javascript_alerts";

        }

    public String getAttributeHref(String nameButton) {
        return driver.findElement(btnForLink(nameButton)).getAttribute("href");
    }
    public void clickOnLink(String nameButton) {
        driver.findElement(btnForLink(nameButton)).click();
    }
    public void scrollToFooter(By element) {
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(element));

    }
    public void clickOnAlert() {
        clickOnLink(buttonJavaScriptAlert());
    }

    public void clickOnAlertJsExecutor(String nameButton) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(btnForLink(nameButton)));
    }

    }
