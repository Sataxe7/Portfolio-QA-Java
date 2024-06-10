package PageObject.page_object;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

// page_url = https://lms.ithillel.ua/auth/registration/confirm-email
public class ConfirmEmailPage {

    private SelenideElement emailFilds() {
        return $x("//input[@id='confirm-email-code']");
    }

    private SelenideElement submitBtn() {
        return $x("//button[@type='submit']");
    }


    public ConfirmEmailPage fillingEmail(String code) {
        emailFilds().setValue(code);
        return this;
    }

    public RecommendationsPage clickBySunmit() {
        submitBtn().click();
        return Selenide.page(RecommendationsPage.class);
    }

}