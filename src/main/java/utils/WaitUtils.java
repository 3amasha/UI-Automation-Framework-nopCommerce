package utils;

import driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    // Wait until element is visible on the page.
    public static WebElement waitForElementToBeVisible(By locator) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitForAllElementsToBeVisible(By locator) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static Boolean waitForElementToBeInvisible(By locator) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


    // Wait until element is clickable.
    public static WebElement waitForElementToBeClickable(By locator) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
    }

    // Wait until the alert is present.
    public static Alert waitForAlertPresent() {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // Wait until the page title contains expected text.
    public static boolean waitForTitleContains(String titleFragment) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.titleContains(titleFragment));
    }

    // Custom wait with timeout override.
    public static WebElement waitForElementVisible(By locator, int timeoutSeconds) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait until element contains expected text.
    public static boolean waitForTextInElement(By locator, String text) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    // Wait until the current URL contains the expected text.
    public static boolean waitForUrlToContain(String expectedUrlPart, int timeoutSeconds) {
        WebDriver driver = DriverManager.getDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
            return wait.until(ExpectedConditions.urlContains(expectedUrlPart));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Waits until the URL exactly matches the expected one.
     *
     * @param expectedUrl The exact expected URL
     * @return Boolean indicating if the URL matches
     */
    public static Boolean waitUntilUrlToBe(String expectedUrl) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    /**
     * Waits until the URL contains the expected part.
     *
     * @param partialUrlText Part of the expected URL
     * @return Boolean indicating if the URL contains the expected part
     */
    public static Boolean waitUntilUrlContains(String partialUrlText) {
        WebDriver driver = DriverManager.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlContains(partialUrlText));
    }
}
