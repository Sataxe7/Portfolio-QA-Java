package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class WebDriverFactory {
    private static WebDriver driver;

    private WebDriverFactory() {
    }

    public static WebDriver initDriver(Browsers browser) {
        switch (browser) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                return driver;
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                return driver;
            }
            case EDGE: {
                WebDriverManager.edgedriver().setup();
                return driver;
            }

        }
        return null;
    }
    public  static WebDriver initDriver() {
        String browserName=System.getProperty("browserName","chrome");
        try {
            return initDriver(Browsers.valueOf(browserName.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println(Browsers.valueOf(browserName.toUpperCase()));
            System.out.println("this browser is not supported ");
            System.exit(-1);
        }
        return null;
    }
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
}
}
}

