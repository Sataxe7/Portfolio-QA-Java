package PageObject.page_object;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = https://lms.ithillel.ua/auth
public class AuthPage {

    /**
     * Locators for form login and registrations
     * */
    private SelenideElement form() {
        return $x("//app-login[contains(@class,'page-login')]");
    }

    private SelenideElement registrationBtn() {
        return $x("//a[contains(@href,'registration')]");
    }


    public AuthPage checkFormLoginAndRegistrations() {
        form().shouldBe(visible, Duration.ofSeconds(60));
        return this;
    }

    public RegistrationPage clickByRegistrationBtn() {
        registrationBtn().click();
        return page(RegistrationPage.class);
    }


}
