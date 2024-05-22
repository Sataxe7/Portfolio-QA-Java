package ui.malinator_service;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

// page_url = https://www.mailinator.com

public class MailinatorMainPage {

    public static String mailinator_main_page = "https://www.mailinator.com";

    private SelenideElement search() {
        return $x("//input[@id='search']");
    }

    public PublicMassegePage searchMail(String mail) {
        search().shouldBe(Condition.visible).setValue(mail).pressEnter();
        return Selenide.page(PublicMassegePage.class);
    }
}
