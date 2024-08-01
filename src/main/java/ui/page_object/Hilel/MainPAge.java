package ui.page_object.Hilel;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.page_object.AuthPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.page;

// page_url = https://ithillel.ua/
public class MainPAge {

    /**
     * Locators for //nav[@class='site-nav'] block
     * */

    private SelenideElement authPageBtn() {
        return $x("//a[@class='site-nav-btn -lms']");
    }


    /**
     * Locator for consultation button
     * */
    /*private SelenideElement consultationBnt() {
        return $(byId("#btn-consultation-hero"));
    }*/
    private SelenideElement consultationBnt() {
        return $x("//button[@id='btn-consultation-hero']");
    }

    @Step("Click by consultation button on the Main page")
    public ConsultationPage clickByConsultationBnt() {
        consultationBnt().click();
        return page(ConsultationPage.class);
    }

    public AuthPage clickByAuthPageBtn() {
        authPageBtn().click();
        return page(AuthPage.class);
    }



}