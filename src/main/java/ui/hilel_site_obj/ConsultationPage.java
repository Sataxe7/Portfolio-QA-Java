package ui.hilel_site_obj;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$x;

// page_url = https://ithillel.ua/#consultation
public class ConsultationPage {


    public SelenideElement inputName() {
        return $x("//*[@id='input-name-consultation']");
    }
@Step ("Input name in field name")
    public ConsultationPage clickOnInputName(String input) {
        inputName().setValue(input);
        return this;
    }

    public SelenideElement inputEmail() {
        return $x("//*[@id='input-email-consultation']");
    }
@Step ("Input Email data")
    public ConsultationPage clickOnEmail(String email) {
        inputEmail().setValue(email);
        return this;
    }

    public SelenideElement inputPhone() {
        return $x("//*[@id='input-tel-consultation']");
    }
@Step("Input phone data")
    public ConsultationPage clickOnPhone(String phone) {
        inputPhone().setValue(phone);
        return this;
    }

    public SelenideElement inputCourse() {
        return $x("//button[@id='listbox-btn-input-course-consultation']");
    }
@Step ("Choose course from scroll-menu")
    public SelenideElement chooseCourse(String name) {
        return $x("//span[@class='listbox_text' and text()='" + name + "']");
    }

    public ConsultationPage clickOnCourse(String courseName) {
        inputCourse().click();
        chooseCourse(courseName).click();
        return this;
    }

    public SelenideElement inputPrivacyBox() {
        return $x("(//span[@class='checkbox_checkmark'])[1]");

    }
@Step("Accept check box")
    public ConsultationPage clickOnCheckBox() {
        inputPrivacyBox().click();
        return this;
    }

    public SelenideElement inputRequest() {
        return $x("(//button[@type='submit']) [2]");
    }
@Step("Click on request consultation")
    public ConsultationPage clickOnRequest() {
        inputRequest().click();
        return this;
    }

    public SelenideElement messageRequestIs() {
        return $x("//*[@class='p-xli c-green form-result_message']");
    }
@Step("Check Message of request Consultation ")
    public ConsultationPage checkMessageRequest() {
        messageRequestIs().shouldHave(exactText("Відправлено"));
        return this;
    }
}
