package PageObject.page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

// page_url = https://lms.ithillel.ua/auth/registration/password
public class PasswordPage {

    public static final String PASSWORD = "autotestHillel24";

    private SelenideElement passwordInput() {
        return $x("//input[@id='password-confirm-new']");
    }

    private SelenideElement confirmPasswordInput() {
        return $x("//input[@id='password-confirm-confirm']");
    }

    private SelenideElement nextBtn() {
        return $x("//button[@type='submit']");
    }

    public ConfirmEmailPage fillingPasswordInput(String pass, String confirmPass) {
        passwordInput().shouldBe(Condition.visible).setValue(pass);
        confirmPasswordInput().setValue(confirmPass);
        nextBtn().click();
        return page(ConfirmEmailPage.class);
    }
}