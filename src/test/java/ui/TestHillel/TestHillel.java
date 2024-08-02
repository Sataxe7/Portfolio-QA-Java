package ui.TestHillel;
import driverManager.BaseTestHilel;
import io.qameta.allure.Step;
import ui.page_object.Hilel.ConsultationPage;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import ui.page_object.Hilel.MainPAge;

import static com.codeborne.selenide.Selenide.open;

public class TestHillel extends BaseTestHilel {
   MainPAge mainPAge = new MainPAge();
    ConsultationPage consultationPage = new ConsultationPage();
    @BeforeClass
    public void setUp() {
        open("https://ithillel.ua/");
    }

    @Owner("Olek")
    @Description("Check consultation request")
    @Test
    public void testConsultationForm() {
        clickByConsultationBtn();
        inputName("Alex");
        inputEmail("Gaev@gmail.com");
        inputPhone("435437842");
        selectCourse("React");
        checkCheckBox();
        clickRequest();
        verifyMessageRequest();
    }

    @Step("Click on the Consultation button")
    private void clickByConsultationBtn() {
        mainPAge.clickByConsultationBtn();
    }

    @Step("Input name: {0}")
    private void inputName(String name) {
        consultationPage.clickOnInputName(name);
    }

    @Step("Input email: {0}")
    private void inputEmail(String email) {
        consultationPage.clickOnEmail(email);
    }

    @Step("Input phone: {0}")
    private void inputPhone(String phone) {
        consultationPage.clickOnPhone(phone);
    }

    @Step("Select course: {0}")
    private void selectCourse(String course) {
        consultationPage.clickOnCourse(course);
    }

    @Step("Check the checkbox")
    private void checkCheckBox() {
        consultationPage.clickOnCheckBox();
    }

    @Step("Click the request button")
    private void clickRequest() {
        consultationPage.clickOnRequest();
    }

    @Step("Verify the request message")
    private void verifyMessageRequest() {
        consultationPage.checkMessageRequest();
    }
    }
