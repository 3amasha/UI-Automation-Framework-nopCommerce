package base;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WaitUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static utils.WaitUtils.waitForElementToBeVisible;

/**
 * BasePage class encapsulates common WebDriver interactions that can be reused across all Page Object classes.
 * Promotes code reuse and separation of concerns in Page Object Model (POM) design.
 */
public class BasePage {
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    // WebDriver instance for all page-level interactions
    protected WebDriver driver;

    // Constructor to initialize driver
    public BasePage() {
        this.driver = DriverManager.getDriver();
    }

    /* *************************************************************************************************************
     * Element Interaction Utilities
     **************************************************************************************************************/

    /**
     * Clicks on an element after waiting for it to be clickable.
     *
     * @param locator By locator of the element
     */
    public void clickOnElement(By locator) {
        WaitUtils.waitForElementToBeVisible(locator);
        WaitUtils.waitForElementToBeClickable(locator).click();
    }

    /**
     * Types text into an input field after waiting for it to be visible.
     *
     * @param locator By locator of the input field
     * @param text    Text to type into the input field
     */
    public void enterText(By locator, String text) {
        waitForElementToBeVisible(locator).sendKeys(text);
    }

    /**
     * Clears the text of an input field after waiting for it to be visible.
     *
     * @param locator By locator of the input field
     */
    public void clearText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
    }

    /**
     * Retrieves the text of an element after waiting for it to be visible.
     *
     * @param locator By locator of the element
     * @return Text content of the element
     */
    public String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }

    /**
     * Retrieves the text of all elements matching the locator after waiting for them to be visible.
     *
     * @param locator By locator of the elements
     * @return List of text content from all matching elements
     */
    public List<String> getElementsText(By locator) {
        List<WebElement> elements = WaitUtils.waitForAllElementsToBeVisible(locator);
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a WebElement by its locator after waiting for it to be visible.
     *
     * @param locator By locator of the element
     * @return WebElement found by the locator
     */
    public WebElement byToWebElement(By locator) {
        return waitForElementToBeVisible(locator);
    }

    /**
     * Checks if an element is displayed on the page
     *
     * @param locator By locator of the element to check
     * @return true if element is displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        try {
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not found or not displayed: {}", locator, e);
            return false;
        }
    }

    public String getAttribute(By locator, String attributeName) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getAttribute(attributeName);
    }

    /* *************************************************************************************************************
     * Dropdown Utilities
     **************************************************************************************************************/

    /**
     * Selects an option in a dropdown by visible text
     *
     * @param locator     By locator of the <select> element
     * @param visibleText The visible text of the option
     */
    public void selectDropdownByVisibleText(By locator, String visibleText) {
        WebElement dropdown = byToWebElement(locator);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    /**
     * Selects an option in a dropdown by value
     *
     * @param locator By locator of the <select> element
     * @param value   The value attribute of the option
     */
    public void selectDropdownByValue(By locator, String value) {
        WebElement dropdown = byToWebElement(locator);
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    /**
     * Selects an option in a dropdown by index
     *
     * @param locator By locator of the <select> element
     * @param index   The index of the option (0-based)
     */
    public void selectDropdownByIndex(By locator, int index) {
        WebElement dropdown = byToWebElement(locator);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }

    /**
     * Returns the visible text of the currently selected option
     *
     * @param locator By locator of the <select> element
     * @return selected option text
     */
    public String getSelectedDropdownOption(By locator) {
        WebElement dropdown = byToWebElement(locator);
        Select select = new Select(dropdown);
        return select.getFirstSelectedOption().getText();
    }

    /**
     * Returns all dropdown options as a list of visible text strings
     *
     * @param locator By locator of the <select> element
     * @return List of option texts
     */
    public List<String> getDropdownOptions(By locator) {
        WebElement dropdown = byToWebElement(locator);
        Select select = new Select(dropdown);
        return select.getOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    /**
     * Checks if a specific option exists in the dropdown
     *
     * @param locator       By locator of the <select> element
     * @param optionToCheck Text to check
     * @return true if option is found, false otherwise
     */
    public boolean isOptionInDropdown(By locator, String optionToCheck) {
        List<String> options = getDropdownOptions(locator);
        return options.stream().anyMatch(opt -> opt.equalsIgnoreCase(optionToCheck));
    }

    /* *************************************************************************************************************
     * Redirection Validation Utilities
     **************************************************************************************************************/

    /**
     * Validates redirection by waiting for a unique element on the new page.
     * You can use isElementDisplayed(By locator) instead of this method as well.
     *
     * @param elementOnNewPage A unique By locator that only exists on the target page
     */
    public boolean validateRedirectionByElement(By elementOnNewPage) {
        try {
            return WaitUtils.waitForElementToBeVisible(elementOnNewPage).isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not found during redirection validation: {}", elementOnNewPage, e);
            return false;
        }
    }

    public Boolean validateRedirectionByUrl(String expectedUrl) {
        try {
            return WaitUtils.waitUntilUrlToBe(expectedUrl);
        } catch (Exception e) {
            logger.warn("Error during URL redirection validation: {}", expectedUrl, e);
            return false;
        }
    }

    /* *************************************************************************************************************
     * Random Number Generation Utilities
     **************************************************************************************************************/

    /**
     * Generates a random integer between 1 and maxValue (inclusive).
     *
     * @param maxValue The maximum value (must be >= 1)
     * @return A random integer between 1 and maxValue
     * @throws IllegalArgumentException if maxValue < 1
     */
    public int generateRandomNumber(int maxValue) {
        if (maxValue < 1) {
            throw new IllegalArgumentException("maxValue must be >= 1");
        }
        return ThreadLocalRandom.current().nextInt(1, maxValue + 1);
    }

    /**
     * Generates a set of unique random integers between 1 and maxValue (inclusive).
     *
     * @param count    The number of unique integers to generate
     * @param maxValue The maximum possible value (must be >= count)
     * @return A Set of unique random integers
     * @throws IllegalArgumentException if count > maxValue or inputs are invalid
     */
    public Set<Integer> generateUniqueNumbers(int count, int maxValue) {
        if (count < 1) {
            logger.warn("Count of generated random numbers must be >= 1, but was: {}", count);
            throw new IllegalArgumentException("count must be >= 1");
        }
        if (maxValue < count) {
            logger.warn("maxValue of generated random must be >= count, but was: maxValue={}, count={}", maxValue, count);
            throw new IllegalArgumentException("maxValue must be >= count");
        }

        Set<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size() < count) {
            uniqueNumbers.add(generateRandomNumber(maxValue));
        }

        return uniqueNumbers;
    }

    /* *************************************************************************************************************
     * Alert Handling Utilities
     **************************************************************************************************************/

    public void acceptAlert() {
        try {
            WaitUtils.waitForAlertPresent().accept();
            logger.info("Alert accepted successfully.");
        } catch (Exception e) {
            logger.error("Failed to accept alert: {}", e.getMessage());
        }
    }

    public void dismissAlert() {
        try {
            WaitUtils.waitForAlertPresent().dismiss();
            logger.info("Alert dismissed successfully.");
        } catch (Exception e) {
            logger.error("Failed to dismiss alert: {}", e.getMessage());
        }
    }

    public String getAlertText() {
        try {
            String alertText = WaitUtils.waitForAlertPresent().getText();
            logger.info("Alert text retrieved: {}", alertText);
            return alertText;
        } catch (Exception e) {
            logger.error("Failed to retrieve alert text: {}", e.getMessage());
            return null;
        }
    }

    public void sendTextToAlert(String text) {
        try {
            WaitUtils.waitForAlertPresent().sendKeys(text);
            logger.info("Sent text to alert: {}", text);
        } catch (Exception e) {
            logger.error("Failed to send keys to alert: {}", e.getMessage());
        }
    }

    public boolean isAlertPresent() {
        try {
            WaitUtils.waitForAlertPresent();
            logger.info("Alert is present.");
            return true; // Alert is present
        } catch (Exception e) {
            logger.warn("No alert present: {}", e.getMessage());
            return false; // No alert found
        }
    }

    public boolean isMessagePresentInAlert(String expectedMessage) {
        if (isAlertPresent()) {
            String alertText = getAlertText();
            boolean isMessagePresent = alertText.contains(expectedMessage);
            if (isMessagePresent) {
                logger.info("Expected message '{}' is present in the alert.", expectedMessage);
            } else {
                logger.warn("Expected message '{}' is NOT present in the alert.", expectedMessage);
            }
            return isMessagePresent;
        } else {
            logger.warn("No alert present to check for message.");
            return false;
        }
    }


}
