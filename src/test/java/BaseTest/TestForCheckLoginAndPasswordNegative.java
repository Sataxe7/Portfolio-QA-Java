package BaseTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class TestForCheckLoginAndPasswordNegative extends TestForCheckLoginAndPasswordPositive {

    @Override
    @BeforeClass
    public void setUp() {
        super.setUp();
    }

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

