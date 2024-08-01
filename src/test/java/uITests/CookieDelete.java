package uITests;

import driverManager.Listener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import ui.LoginPasswordPage;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static ui.LoginPasswordPage.EXPECTEDURL;
import static ui.LoginPasswordPage.LOGINPAGEURL;
@Listeners(Listener.class)
public class CookieDelete extends BaseTest {
    LoginPasswordPage loginPasswordPage;
    String currentUrl;

    @BeforeMethod
    public void setUp() {
        openUrl(LOGINPAGEURL);
        loginPasswordPage = new LoginPasswordPage(driver);
        currentUrl = driver.getCurrentUrl();
    }


    @Test
    public void checkCookeis() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        // Выполняем логин
        loginPasswordPage.selectLogin("tomsmith");
        loginPasswordPage.selectPassword("SuperSecretPassword!");
        loginPasswordPage.selectButtonLogIn();

        wait.until(ExpectedConditions.urlToBe(EXPECTEDURL));
        currentUrl = driver.getCurrentUrl();

        Assert.assertEquals(currentUrl, EXPECTEDURL, "Failed to navigate to the secure area after login.");

        WebElement tooltipElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertEquals(loginPasswordPage.verifyTooltipText(), "You logged into a secure area!", "Tooltip text is incorrect after login.");

        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " " + cookie.getValue() + " " + cookie.getExpiry());
        }

        // Удаляем все cookies
        driver.manage().deleteAllCookies();

        // Явное ожидание, что cookies будут удалены
        wait.until(driver -> driver.manage().getCookies().isEmpty());

        // Проверяем количество оставшихся cookies
        Set<Cookie> remainingCookies = driver.manage().getCookies();
        System.out.println("Number of remaining cookies after deletion: " + remainingCookies.size());
        Assert.assertEquals(remainingCookies.size(), 0, "Expected all cookies to be deleted.");

        // Обновляем страницу
        driver.navigate().refresh();

        // Явное ожидание появления текста тултипа после обновления страницы
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertEquals(loginPasswordPage.verifyTooltipText(), "You must login to view the secure area!", "Tooltip text is incorrect after refresh.");
    }
}



