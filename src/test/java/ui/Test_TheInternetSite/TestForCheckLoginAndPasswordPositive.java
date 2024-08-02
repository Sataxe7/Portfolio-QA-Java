package ui.Test_TheInternetSite;

import org.testng.annotations.BeforeMethod;
import driverManager.BaseTest;
import ui.TheInternet.LoginPasswordPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static ui.TheInternet.LoginPasswordPage.EXPECTEDURL;
import static ui.TheInternet.LoginPasswordPage.LOGINPAGEURL;

public class TestForCheckLoginAndPasswordPositive extends BaseTest {

    LoginPasswordPage loginPasswordPage;
    String currentUrl;



    @BeforeMethod
    public void setUp() {
        openUrl(LOGINPAGEURL);
        loginPasswordPage = new LoginPasswordPage(driver);
        currentUrl = driver.getCurrentUrl();
    }

    @Test
    public void checkLoginForm() {
        loginPasswordPage.selectLogin("tomsmith");
        loginPasswordPage.selectPassword("SuperSecretPassword!");
        loginPasswordPage.selectButtonLogIn();
        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, EXPECTEDURL, "Failed");
        Assert.assertEquals(loginPasswordPage.verifyTooltipText(), "You logged into a secure area!");
        loginPasswordPage.selectButtonLogOut();
        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(LOGINPAGEURL, currentUrl, "Failed Redirect");

        Assert.assertEquals(loginPasswordPage.verifyTooltipText(), "You logged out of the secure area!", "Text isnt expectable");



    }

}
