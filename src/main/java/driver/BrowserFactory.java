package driver;

import config.ConfigReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;

public class BrowserFactory {

    public static WebDriver getBrowserDriver(String browserName) {
        return switch (browserName.toUpperCase()) {
            case "CHROME" -> new ChromeDriver(getChromeOptions());//getChromeOptions()
            case "FIREFOX" -> new FirefoxDriver(getFirefoxOptions());//getFirefoxOptions()
            case "EDGE" -> new EdgeDriver(getEdgeOptions());//getEdgeOptions()
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        // Set browser startup arguments
        options.addArguments(
                "start-maximized",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-infobars",
                "--disable-dev-shm-usage",
                "--disable-extensions",
                "--lang=en-US" // ✅ Set language to English
        );

        // Set page load strategy
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        // Disable password manager, notifications, autofill
        options.setExperimentalOption("prefs", Map.of(
                "profile.default_content_setting_values.notifications", 2,
                "credential_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile.enable", false
        ));

        // If not local, run in headless mode
        // if (!ConfigReader.get("executionType").equalsIgnoreCase("local")) {
        //   options.addArguments("--headless");
        //  }

        return options;
    }


    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized", "--disable-notifications", "--disable-popup-blocking", "--disable-infobars",
                "--disable-dev-shm-usage", "--disable-extensions");

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setAcceptInsecureCerts(true);

        if (!ConfigReader.get("executionType").equalsIgnoreCase("local")) {
            options.addArguments("--headless");
        }

        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("start-maximized", "--disable-notifications", "--disable-popup-blocking", "--disable-infobars",
                "--disable-dev-shm-usage", "--disable-extensions");

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setExperimentalOption("prefs", Map.of
                (
                        "profile.default_content_setting_values.notifications", 2,
                        "credential_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "autofill.profile.enable", false
                )
        );

        if (!ConfigReader.get("executionType").equalsIgnoreCase("local")) {
            options.addArguments("--headless");
        }

        return options;
    }


}