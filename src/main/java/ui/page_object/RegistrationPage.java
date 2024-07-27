package ui.page_object;

import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;

// page_url = https://lms.ithillel.ua/auth/registration/contacts
public class RegistrationPage {

    /**
     * Locators for registration form
     */
    private SelenideElement nameInput() {
        return $x("//input[@id='contacts_first_name']");
    }

    private SelenideElement surnameInput() {
        return $x("//input[@id='contacts_last_name']");
    }

    private SelenideElement mailInput() {
        return $x("//input[@id='contacts_email']");
    }

    private SelenideElement telephoneInput() {
        return $x("//input[@id='contacts_phone']");
    }

    private SelenideElement dataProcessingCheckBox() {
        return $x("//input[@formcontrolname='data_processing']");
    }

    private SelenideElement termsCheckBox() {
        return $x("//input[@formcontrolname='terms']");
    }

    private SelenideElement nextBtn() {
        return $x("//button[@type='submit']");
    }

    public PasswordPage fillingRegistrationForm(String name, String surname) {
        nameInput().shouldBe(visible).setValue(name);
        surnameInput().setValue(surname);
        dataProcessingCheckBox().click();

        termsCheckBox().click();
        nextBtn().click();
        return page(PasswordPage.class);
    }
    public PasswordPage fillingRegistrationFormTwo(String mail, String telNumber) {
        mailInput().setValue(mail);
        telephoneInput().setValue(telNumber);
        dataProcessingCheckBox().click();
        termsCheckBox().click();
        nextBtn().click();
        return page(PasswordPage.class);
    }

    public PasswordPage fillingRegistrationForm(String... value) {
        List<String> listValue = Arrays.asList(value);
        nameInput().shouldBe(visible).setValue(listValue.get(0));
        surnameInput().setValue(listValue.get(1));
        mailInput().setValue(listValue.get(2));
        telephoneInput().setValue(listValue.get(3));

        dataProcessingCheckBox().click();
        termsCheckBox().click();
        nextBtn().click();
        return page(PasswordPage.class);
    }
}