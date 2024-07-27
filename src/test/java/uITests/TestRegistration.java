
package uITests;

import com.codeborne.selenide.Selenide;
import driverManager.CommonMethods;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;
import ui.malinator_service.MailinatorMainPage;
import ui.page_object.ConfirmEmailPage;
import ui.page_object.ManePage;
import ui.page_object.RecommendationsPage;

public class TestRegistration extends BaseTestHilel {
    ManePage manePage = new ManePage();
    MailinatorMainPage mailinatorMainPage = new MailinatorMainPage();
    ConfirmEmailPage confirmEmailPage = new ConfirmEmailPage();
    private String mail = CommonMethods.randomMail();
    private String confirmCod;

    public TestRegistration() {
    }

    @Test
    public void testRegistration() {
        System.out.println(this.mail);
        Selenide.open("https://ithillel.ua/");
        this.manePage.clickByAuthPageBtn().checkFormLoginAndRegistrations().clickByRegistrationBtn().fillingRegistrationForm("Autotest", "Autotest").fillingPasswordInput("autotestHillel24", "autotestHillel24","063032312",mail);
        Selenide.switchTo().newWindow(WindowType.TAB);
        Selenide.switchTo().window(1);
        Selenide.open("https://www.mailinator.com");
        this.confirmCod = this.mailinatorMainPage.searchMail(this.mail).openLetterAndGetConfirmCode();
        Selenide.switchTo().window(0);
        this.confirmEmailPage.fillingEmail(this.confirmCod).clickBySunmit();
        CommonMethods.checkingContainsURL(RecommendationsPage.recommendationsPageUrl);
    }
}
