package uITests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.LoginPasswordPage;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static ui.LoginPasswordPage.EXPECTEDURL;
import static ui.LoginPasswordPage.LOGINPAGEURL;

public class CookieDelete extends TestForCheckLoginAndPasswordPositive {

    @BeforeClass
    public void setUp() {
        openUrl(LOGINPAGEURL);
        loginPasswordPage = new LoginPasswordPage(driver);
        currentUrl = driver.getCurrentUrl();
    }

    @Test
    public void checkCookeis() {
        loginPasswordPage.selectLogin("tomsmith");
        loginPasswordPage.selectPassword("SuperSecretPassword!");
        loginPasswordPage.selectButtonLogIn();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe(EXPECTEDURL));
        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, EXPECTEDURL);
        Assert.assertEquals(loginPasswordPage.verifyTooltipText(), "You logged into a secure area!");
        Set <Cookie> cookies = driver.manage().getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + " " + cookie.getValue() + " " + cookie.getExpiry());
        }
          driver.manage().deleteAllCookies();
        Set <Cookie> remainingCookies = driver.manage().getCookies();
        System.out.println("Number of remaining cookies after deletion: " + remainingCookies.size());
        Assert.assertEquals(remainingCookies.size(), 0, "Expected all cookies to be deleted");
        driver.navigate().refresh();
        Assert.assertEquals(loginPasswordPage.verifyTooltipText(),"You must login to view the secure area!");

        }

            }






