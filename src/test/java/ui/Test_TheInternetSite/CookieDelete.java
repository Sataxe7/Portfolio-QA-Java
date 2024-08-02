package ui.Test_TheInternetSite;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import driverManager.BaseTest;
import ui.TheInternet.LoginPasswordPage;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static ui.TheInternet.LoginPasswordPage.EXPECTEDURL;
import static ui.TheInternet.LoginPasswordPage.LOGINPAGEURL;

public class CookieDelete extends BaseTest {
    LoginPasswordPage loginPasswordPage;
    String currentUrl;

    @BeforeMethod
    public void setUp() {
        openUrl(LOGINPAGEURL);
        loginPasswordPage = new LoginPasswordPage(driver);
        currentUrl = driver.getCurrentUrl();
    }
    @Owner("Olek")
    @Description("Cookie Delete")

    @Test
    public void checkCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        performLogin();
        verifyLoginSuccess(wait);
        printCookies();
        deleteAllCookies(wait);
        verifyCookiesDeleted(wait);
    }

    @Step("Perform login with username 'tomsmith' and password 'SuperSecretPassword!'")
    private void performLogin() {
        loginPasswordPage.selectLogin("tomsmith");
        loginPasswordPage.selectPassword("SuperSecretPassword!");
        loginPasswordPage.selectButtonLogIn();
    }

    @Step("Verify login was successful and navigate to the secure area")
    private void verifyLoginSuccess(WebDriverWait wait) {
        wait.until(ExpectedConditions.urlToBe(EXPECTEDURL));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, EXPECTEDURL, "Failed to navigate to the secure area after login.");

        WebElement tooltipElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertEquals(loginPasswordPage.verifyTooltipText(), "You logged into a secure area!", "Tooltip text is incorrect after login.");
    }

    @Step("Print all cookies to the console")
    private void printCookies() {
        Set<Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " " + cookie.getValue() + " " + cookie.getExpiry());
        }
    }

    @Step("Delete all cookies and verify they are removed")
    private void deleteAllCookies(WebDriverWait wait) {
        driver.manage().deleteAllCookies();
        wait.until(driver -> driver.manage().getCookies().isEmpty());
    }

    @Step("Verify that all cookies are deleted and page shows login message after refresh")
    private void verifyCookiesDeleted(WebDriverWait wait) {
        Set<Cookie> remainingCookies = driver.manage().getCookies();
        System.out.println("Number of remaining cookies after deletion: " + remainingCookies.size());
        Assert.assertEquals(remainingCookies.size(), 0, "Expected all cookies to be deleted.");

        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertEquals(loginPasswordPage.verifyTooltipText(), "You must login to view the secure area!", "Tooltip text is incorrect after refresh.");
    }
}




