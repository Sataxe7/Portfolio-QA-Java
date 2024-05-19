package driverManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    protected static WebDriver driver;

    protected WebDriverFactory() {
    }

    public static WebDriver initDriver(Browsers browser) {
        switch (browser) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            case EDGE: {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            }
            default: {
                throw new IllegalArgumentException("This browser is not supported");
            }
        }
        if (driver != null) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        }
        return driver;
    }

    public static WebDriver initDriver() {
        String browserName = System.getProperty("browserName", "chrome");
        try {
            return initDriver(Browsers.valueOf(browserName.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.out.println("This browser is not supported: " + browserName);
            System.exit(-1);
        }
        return null;
    }

    public static void quitDriver() {
        if (driver != null) {
            System.out.println("Quitting driver");
            driver.quit();
            driver = null;
            System.out.println("Driver quit and set to null");
        }
    }
}