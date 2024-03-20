package BaseTest;

import PageObject.ConsultationPage;
import PageObject.LoginPasswordPage;
import PageObject.MainPageHillel;
import driverManager.BaseTestHillel;
import driverManager.Listener;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import static PageObject.MainPageHillel.PAGEMAIN;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
@Listeners(Listener.class)

public class TestHillel  extends BaseTestHillel {
MainPageHillel mainPageHillel=new MainPageHillel();
ConsultationPage consultationPage= new ConsultationPage();






    @Test
    @Owner("Olek")
    @Description("Check consultation_ request_")
    public void requestForConsultation() {
        open("https://ithillel.ua/");
        mainPageHillel.clickByConsultationBtn();
        consultationPage.clickOnInputName("Alex");
        consultationPage.clickOnEmail("Gaev@gmail.com");
        consultationPage.clickOnPhone("435437842");
        consultationPage.clickOnCourse("React");
        consultationPage.clickOnCheckBox();
        consultationPage.clickOnRequest();
        consultationPage.checkMessageRequest();
    }

}
