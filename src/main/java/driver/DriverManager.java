package driver;

import org.openqa.selenium.WebDriver;

public class DriverManager
{
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {}

    public static void createDriver(String browserName)
    {
        WebDriver driver = BrowserFactory.getBrowserDriver(browserName);
        driverThreadLocal.set(driver);
    }

    public static WebDriver getDriver()
    {
        if (driverThreadLocal.get() == null)
        {
            throw new IllegalStateException("WebDriver is not initialized. Call createDriver() first.");
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver()
    {
        if (driverThreadLocal.get() != null)
        {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
}