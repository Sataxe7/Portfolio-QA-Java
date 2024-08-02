package ui.page_object.Hilel;

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


    private SelenideElement phoneNumber() {
        return $x("//*[@id='contacts_phone']");
    }
   private SelenideElement emailInput() {
       return $x("//*[@id='contacts_email']");
   }
    public ConfirmEmailPage fillingPasswordInput(String pass, String confirmPass, String phone, String email) {
        passwordInput().shouldBe(Condition.visible).setValue(pass);
        confirmPasswordInput().setValue(confirmPass);
        phoneNumber().setValue(phone);
        emailInput().setValue(email);
        nextBtn().click();
        return page(ConfirmEmailPage.class);
    }
}