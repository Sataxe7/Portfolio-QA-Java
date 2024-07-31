package uITests;
import driverManager.BaseTestHilel;
import ui.hilel_site_obj.ConsultationPage;
import ui.hilel_site_obj.MainPageHillel;
import driverManager.Listener;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import static com.codeborne.selenide.Selenide.open;
@Listeners(Listener.class)
public class TestHillel extends BaseTestHilel {
    MainPageHillel mainPageHillel = new MainPageHillel();
    ConsultationPage consultationPage = new ConsultationPage();
    @BeforeClass
    public void setUp() {
        open("https://ithillel.ua/");
    }

    @Test
    @Owner("Olek")
    @Description("Check consultation request")
    public void requestForConsultation() {
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
