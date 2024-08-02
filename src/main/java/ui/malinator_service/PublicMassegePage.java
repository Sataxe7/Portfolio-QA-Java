package ui.malinator_service;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.*;

// page_url = https://www.mailinator.com/v4/public/inboxes.jsp?to=autotesthillel
public class PublicMassegePage {

    String IFRAME_NAME = "html_msg_body";

    private SelenideElement letter(int numberLetter) {
        return $x("(//tr[@ng-repeat='email in emails'])[" + numberLetter + "]");
    }

    private SelenideElement confirmCode() {
        return $x("//p[text()='Confirmation code']/..//h1");
    }

    public String openLetterAndGetConfirmCode() {
        letter(1).shouldBe(visible, ofSeconds(50));
        letter(1).click();
        switchTo().frame(IFRAME_NAME);
        return confirmCode().scrollIntoView("{block: \"center\"}").getText();
    }
}