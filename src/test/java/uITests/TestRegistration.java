package uITests;


import ui.hilel_site_obj.MainPage;

import ui.malinator_service.MailinatorMainPage;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import ui.page_object.ConfirmEmailPage;
import ui.page_object.ManePage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static driverManager.CommonMethods.checkingContainsURL;
import static driverManager.CommonMethods.randomMail;

import static ui.page_object.PasswordPage.PASSWORD;
import static ui.page_object.RecommendationsPage.recommendationsPageUrl;


public class TestRegistration extends BaseTest {

   ManePage manePage = new ManePage();
    MailinatorMainPage mailinatorMainPage = new MailinatorMainPage();
    ConfirmEmailPage confirmEmailPage = new ConfirmEmailPage();

    private String mail = randomMail();


    private String confirmCod;

    @Test
    public void testRegistration() {
        System.out.println(mail);
        open("https://ithillel.ua/");
        manePage.clickByAuthPageBtn()
                .checkFormLoginAndRegistrations()
                .clickByRegistrationBtn()
                .fillingRegistrationForm("Autotest", "Autotest", mail, "302346894")
                .fillingPasswordInput(PASSWORD, PASSWORD);
        switchTo().newWindow(WindowType.TAB);
        switchTo().window(1);
        open("https://www.mailinator.com");

        confirmCod =  mailinatorMainPage.searchMail(mail)
                .openLetterAndGetConfirmCode();
        switchTo().window(0);
        confirmEmailPage.fillingEmail(confirmCod)
                .clickBySunmit();
        checkingContainsURL(recommendationsPageUrl);
    }
}
