package BaseTest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class TestForCheckLoginAndPasswordNegative extends TestForCheckLoginAndPasswordPositive {





    @Test
    public void checkLoginPage() {
        loginPasswordPage.selectLogin("tomsmith");
        loginPasswordPage.selectPassword("");
        loginPasswordPage.selectButtonLogIn();
        Assert.assertEquals(loginPasswordPage.verifyTooltipText(), "Your password is invalid!");


    }

    @Test

    public void checkMissingUser() {
        loginPasswordPage.selectLogin("");
        loginPasswordPage.selectPassword(("SuperSecretPassword!"));
        loginPasswordPage.selectButtonLogIn();
        Assert.assertEquals(loginPasswordPage.verifyTooltipText(), "Your username is invalid!");

    }

}

