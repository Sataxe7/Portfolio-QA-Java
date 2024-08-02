package ui.TheInternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LoginPasswordPage extends BasePage {
    public static final String EXPECTED_TITLE = "Login and Password ";
    public static final String LOGINPAGEURL = "https://the-internet.herokuapp.com/login";
    public static final String EXPECTEDURL = "https://the-internet.herokuapp.com/secure";

    public LoginPasswordPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    private By loginForm() {
        return By.xpath("//input[@id='username']");
    }

    private By passwordForm() {
        return By.xpath("//input[@id='password']");

    }

    private By flashMessageLogIn() {
        return By.id("flash");
    }


    private By buttonLog() {
        return By.xpath("//button[@class='radius']");

    }

    private By buttonLogOut() {
        return By.xpath("//a[@href='/logout']");
    }

    public void selectLogin(String login) {

        driver.findElement(loginForm()).click();
        driver.findElement(loginForm()).sendKeys(login);
    }

    public void selectPassword(String pass) {

        driver.findElement(passwordForm()).click();
        driver.findElement(passwordForm()).sendKeys(pass);
    }

    public void selectButtonLogIn() {

        driver.findElement(buttonLog()).click();
    }

    public String verifyTooltipText() {

        WebElement tooltipElement = driver.findElement(flashMessageLogIn());
        String tooltipText = tooltipElement.getText().replaceAll("[^a-zA-Z0-9\\s!]", "").trim();

        return tooltipText;
    }


    public void selectButtonLogOut() {
        driver.findElement(buttonLogOut()).click();
    }


}

