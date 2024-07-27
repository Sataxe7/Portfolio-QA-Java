package driverManager;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class CommonMethods {

    public static String randomMail() {
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt();
        return "autotest" + randomNumber + "@mailinator.com";
    }

    public static void checkingContainsURL(String expectedValue) {
        try {
            Wait<WebDriver> wait = new WebDriverWait(getWebDriver(), Duration.ofMinutes(1));
            wait.until(webDriver -> url().contains(expectedValue));
        } catch (TimeoutException e) {
            System.out.println(url());
            Assert.fail("Url dosen't contains " + expectedValue);
        }
    }
}
