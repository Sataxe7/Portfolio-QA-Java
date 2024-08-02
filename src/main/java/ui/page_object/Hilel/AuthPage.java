package ui.page_object.Hilel;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;

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

    // page_url = https://ithillel.ua/#consultation
    public static class ConsultationPage {

        private SelenideElement formConsultation() {
            return $x("//form[@id='form-consultation']");
        }

        private SelenideElement inputName() {
            return $x("(//input[@name='name'])[1]");
        }

        private SelenideElement inputMail() {
            return $x("(//input[@type='email'])[1]");
        }

        private SelenideElement telCodSelector() {
            return $x("(//div[@class='iti__flag-container'])[1]");
        }

        private SelenideElement dataCountryCod(String countryCod) {
            return $x("(//div[@class='iti__flag-container'])[1]//ul//li[@data-country-code='" + countryCod + "']");
        }

        private SelenideElement inputTelNumber() {
            return $x("//input[@id='input-tel-consultation']");
        }

        private SelenideElement listThisCourseSelector() {
            return $x("(//span[@class='listbox-btn_content'])[1]");
        }

        private SelenideElement cursName(String name) {
            return $x("//span[text()='" + name + "']");
        }

        private SelenideElement inputComment() {
            return $x("//textarea[@id='input-comment-consultation']");
        }

        private SelenideElement privacyCheckBox() {
            return $x("(//span[@class='checkbox_checkmark'])[10]");
        }

        private SelenideElement submitBtn() {
            return $x("(//button[@type='submit'])[2]");
        }

        private SelenideElement resultText() {
            return $x("//div[@class='form-result_main']/p");
        }




        public ConsultationPage formConsultationIsVisible() {
            formConsultation().shouldBe(visible);
            return this;
        }

        @Step("Filling input name on the Consultation page")
        public ConsultationPage filingInputName(String expectedName) {
            inputName().setValue(expectedName);
            return this;
        }

        @Step("Filling input mail on the Consultation page")
        public ConsultationPage filingInputMail(String expectedMail) {
            inputMail().setValue(expectedMail);
            return this;
        }

        @Step("Choose tell cod on the Consultation page")
        public ConsultationPage choosesTelCode(String countryCod) {
            telCodSelector().click();
            dataCountryCod(countryCod).scrollIntoView("{block: \"center\"}").click();
            return this;
        }

        @Step("Filling input tell number on the Consultation page")
        public ConsultationPage filingInputTelNumber(String telNumber) {
            inputTelNumber().setValue(telNumber);
            return this;
        }

        @Step("Choose course on the Consultation page")
        public ConsultationPage choosesCourse(String courseName) {
            listThisCourseSelector().click();
            cursName(courseName).click();
            return this;
        }

        @Step("Enter comment on the Consultation page")
        public ConsultationPage enterComment(String comment) {
            inputComment().setValue(comment);
            return this;
        }

        @Step("Click privacy check box on the Consultation page")
        public ConsultationPage clickPrivacyCheckBox() {
            privacyCheckBox().click();
            return this;
        }

        @Step("Click by submit on the Consultation page")
        public ConsultationPage clickBySubmitBtn() {
            submitBtn().click();
            return this;
        }

        @Step("Checks result text on the Consultation page")
        public ConsultationPage checksResultText() {
            resultText().shouldHave(exactText("Відправлено"));
            return this;
        }
    }
}