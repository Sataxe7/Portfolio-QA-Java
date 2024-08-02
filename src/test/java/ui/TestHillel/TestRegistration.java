
package ui.TestHillel;

import com.codeborne.selenide.Selenide;
import driverManager.BaseTestHilel;
import driverManager.CommonMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import ui.malinator_service.MailinatorMainPage;
import ui.page_object.Hilel.ConfirmEmailPage;
import ui.page_object.Hilel.MainPAge;
import ui.page_object.Hilel.PasswordPage;
import ui.page_object.Hilel.RecommendationsPage;

public class TestRegistration extends BaseTestHilel {
    MainPAge manePage = new MainPAge();
    PasswordPage passwordPage = new PasswordPage();
    MailinatorMainPage mailinatorMainPage = new MailinatorMainPage();
    ConfirmEmailPage confirmEmailPage = new ConfirmEmailPage();
    private String mail = CommonMethods.randomMail();
    @Owner("Olek")
    @Description("Check Registration ")
    @Test
    public void testRegistration() {
        openWebsite();
        performRegistrationSteps();
        String confirmCode = getConfirmationCode();
        confirmEmail(confirmCode);
        verifyRedirection();
    }

    @Step("Open the website: https://ithillel.ua/")
    private void openWebsite() {
        Selenide.open("https://ithillel.ua/");
    }

    @Step("Click Auth Page button, check form, click Registration button, and fill the registration form")
    private void performRegistrationSteps() {
        manePage.clickByAuthPageBtn()
                .checkFormLoginAndRegistrations()
                .clickByRegistrationBtn()
                .fillingRegistrationForm("Autotest", "Autotest");
        passwordPage.fillingPasswordInput("autotestHillel24", "autotestHillel24", "063032312", mail);
    }

    @Step("Switch to Mailinator tab and get confirmation code")
    private String getConfirmationCode() {
        Selenide.switchTo().newWindow(WindowType.TAB);
        Selenide.switchTo().window(1);
        Selenide.open("https://www.mailinator.com");
        String confirmCode = mailinatorMainPage.searchMail(mail)
                .openLetterAndGetConfirmCode();
        Selenide.switchTo().window(0);
        return confirmCode;
    }

    @Step("Fill email with confirmation code and click submit")
    private void confirmEmail(String confirmCode) {
        confirmEmailPage.fillingEmail(confirmCode)
                .clickBySubmit();
    }

    @Step("Verify that the user is redirected to recommendations page")
    private void verifyRedirection() {
        CommonMethods.checkingContainsURL(RecommendationsPage.recommendationsPageUrl);
    }
}
