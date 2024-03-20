package BaseTest;





import driverManager.WebDriverFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


import static com.codeborne.selenide.Selenide.open;
import static org.bouncycastle.math.raw.Nat.equalTo;


public class selenideExamples extends WebDriverFactory {
    protected WebDriver driver;
    @BeforeClass

    @Test
    public void checkTest() {
        open("https://ithillel.ua/");



    }


}

