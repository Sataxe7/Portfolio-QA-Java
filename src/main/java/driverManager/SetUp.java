package driverManager;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SetUp {
    static String browserFromJenkins = System.getenv("BROWSERS");
    public static void setUp(Browsers browser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browserCapabilities = capabilities;
        Configuration.browserSize = "1920x1080";
        Configuration.browserPosition = "1x1";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;
        Configuration.browser = null;
        if (browserFromJenkins != null) {
            Configuration.browser = browserFromJenkins;
        } else {
            switch (browser) {
                case EDGE: {
                    Configuration.browser = "edge";
                    return;
                }
                case CHROME: {
                    Configuration.browser = "chrome";
                    return;
                }
                case FIREFOX: {
                    Configuration.browser = "firefox";
                }
            }
        }
    }

    public static void setUp() {
        String browserName = System.getProperty("browserName", "chrome");
        try {
            setUp(Browsers.valueOf(browserName.toUpperCase()));
        } catch (IllegalArgumentException e) {
            System.err.print("This browser is not supported!!!");
            System.exit(-1);
        }
    }
}

