package base;

import config.ConfigReader;
import driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert; // Shared across test methods

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String browser = ConfigReader.get("browser");
        DriverManager.createDriver(browser);

        // Avoid storing in instance variable
        DriverManager.getDriver().manage().window().maximize();

        String url = ConfigReader.get("base.url.shop");
        DriverManager.getDriver().get(url);


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}