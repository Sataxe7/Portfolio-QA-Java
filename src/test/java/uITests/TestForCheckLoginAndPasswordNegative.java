package uITests;

import driverManager.Listener;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Listener.class)

public class TestForCheckLoginAndPasswordNegative extends TestForCheckLoginAndPasswordPositive {





    @Test
    @Owner("Olek")
    @Description ("Check invalid password input")
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

