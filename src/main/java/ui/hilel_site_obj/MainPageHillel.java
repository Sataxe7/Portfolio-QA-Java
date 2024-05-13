package ui.hilel_site_obj;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
/*import ui.lms_Page_obj.RegistatioPage;*/

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

// page_url = https://ithillel.ua/ru?utm_source=google&utm_medium=cpc&utm_campaign=g&utm_content=642444578119&utm_term=&gad_source=1&gclid=Cj0KCQjwhtWvBhD9ARIsAOP0GojIuCD-oGeqOAWOQpLwtSrS9NWnfgEcWjpqZEG8vojECx9ngBo8ESUaAk4bEALw_wcB
public class MainPageHillel{
    public static final String PAGEMAIN = "https://ithillel.ua/";





    private SelenideElement btnConsutltation() {
        return $x("//button[@id='btn-consultation-hero']");
    }
    @Step ("Click on Button consultation Page")
    public ConsultationPage clickByConsultationBtn() {
        btnConsutltation().click();
        return page(ConsultationPage.class);

    }
}
