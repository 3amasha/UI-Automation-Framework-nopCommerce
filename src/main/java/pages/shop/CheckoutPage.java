package pages.shop;

import base.BasePage;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class CheckoutPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutPage.class);


    // Billing address section locators
    private final By savedBillingAddressSelect = By.id("billing-address-select");
    private final By firstName = By.id("BillingNewAddress_FirstName");
    private final By lastName = By.id("BillingNewAddress_LastName");
    private final By email = By.id("BillingNewAddress_Email");
    private final By company = By.id("BillingNewAddress_Company");
    private final By country = By.id("BillingNewAddress_CountryId");
    private final By state = By.id("BillingNewAddress_StateProvinceId");
    private final By city = By.id("BillingNewAddress_City");
    private final By address1 = By.id("BillingNewAddress_Address1");
    private final By address2 = By.id("BillingNewAddress_Address2");
    private final By zibCode = By.id("BillingNewAddress_ZipPostalCode");
    private final By phoneNumber = By.id("BillingNewAddress_PhoneNumber");
    private final By faxNumber = By.id("BillingNewAddress_FaxNumber");
    private final By addressContinueButton = By.xpath("//div[@id='billing-buttons-container']/button[contains (@class , 'new-address-next-step-button')]");

    // Shipping method section locators
    private final By groundRadioBtn = By.id("shippingoption_0");
    private final By nextDayRadioBtn = By.id("shippingoption_1");
    private final By secondDayRadioBtn = By.id("shippingoption_2");
    private final By shippingMethodContinueButton = By.xpath("//div[@id='shipping-method-buttons-container']/button[contains (@class , 'shipping-method-next-step-button')]");

    // Payment method section locators
    private final By moneyOrderRadioBtn = By.id("paymentmethod_0");
    private final By creditCardRadioBtn = By.id("paymentmethod_1");
    private final By paymentMethodContinueButton = By.xpath("//div[@id='payment-method-buttons-container']/button[contains (@class , 'payment-method-next-step-button')]");

    // Payment information section locators
    private final By creditCardType = By.id("CreditCardType");
    private final By cardholderName = By.id("CardholderName");
    private final By cardNumber = By.id("CardNumber");
    private final By expirationMonth = By.id("ExpireMonth");
    private final By expirationYear = By.id("ExpireYear");
    private final By cardCode = By.id("CardCode");
    private final By paymentInfoContinueButton = By.xpath("//div[@id='payment-info-buttons-container']/button[contains (@class , 'payment-info-next-step-button')]");

    //Confirm order section locators
    private final By productsNames = By.cssSelector("a.product-name");
    private final String specificProductName = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='product']//a";
    private final By totalCheckoutPrice = By.cssSelector("td strong");
    private final String totalProductPrice = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='subtotal']//span";
    private final String productQuantity = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='unit-price']//label";
    private final By confirmButton = By.cssSelector("button.confirm-order-next-step-button");

    // success checkout
    private final By successMessage = By.cssSelector("div.order-completed div.title strong");
    private final By orderDetailLink = By.cssSelector("div.details-link a");
    private final By orderCompleteContinueButton = By.cssSelector("button.order-completed-continue-button");
    private final By orderNumber = By.cssSelector("div.order-number strong");
    private final By homePageWelcomeMessage = By.cssSelector("div.topic-block-title h2");


    public CheckoutPage() {
        super(); // Ensures correct driver from ThreadLocal
    }


    /*
     * Billing address section
     */
    public CheckoutPage selectToFillNewBillingAddress() {
        if (isElementDisplayed(savedBillingAddressSelect)) {
            selectDropdownByVisibleText(savedBillingAddressSelect, "New Address");
            logger.info("CheckoutPage, New billing address selected");
        }
        return this;
    }

    public CheckoutPage enterFirstName(String firstNameText) {
        enterText(firstName, firstNameText);
        logger.info("CheckoutPage, First name entered: {}", firstNameText);
        return this;
    }

    public CheckoutPage enterLastName(String lastNameText) {
        enterText(lastName, lastNameText);
        logger.info("CheckoutPage, Last name entered: {}", lastNameText);
        return this;
    }

    public CheckoutPage enterEmail(String emailText) {
        enterText(email, emailText);
        logger.info("CheckoutPage, Email entered: {}", emailText);
        return this;
    }

    public CheckoutPage enterCompany(String companyText) {
        enterText(company, companyText);
        logger.info("CheckoutPage, Company name entered: {}", companyText);
        return this;
    }

    public CheckoutPage selectCountry(String countryText) {
        selectDropdownByVisibleText(country, countryText);
        logger.info("CheckoutPage, Country selected: {}", countryText);
        return this;
    }

    public CheckoutPage selectState(String stateText) {
        selectDropdownByVisibleText(state, stateText);
        logger.info("CheckoutPage, State selected: {}", stateText);
        return this;
    }

    public CheckoutPage enterCity(String cityText) {
        enterText(city, cityText);
        logger.info("CheckoutPage, City entered: {}", cityText);
        return this;
    }

    public CheckoutPage enterAddress1(String address1Text) {
        enterText(address1, address1Text);
        logger.info("CheckoutPage, Address1 entered: {}", address1Text);
        return this;
    }

    public CheckoutPage enterAddress2(String address2Text) {
        enterText(address2, address2Text);
        logger.info("CheckoutPage, Address2 entered: {}", address2Text);
        return this;
    }

    public CheckoutPage enterZipCode(String zipCodeText) {
        enterText(zibCode, zipCodeText);
        logger.info("CheckoutPage, Zip code entered: {}", zipCodeText);
        return this;
    }

    public CheckoutPage enterPhoneNumber(String phoneNumberText) {
        enterText(phoneNumber, phoneNumberText);
        logger.info("CheckoutPage, Phone number entered: {}", phoneNumberText);
        return this;
    }

    public CheckoutPage enterFax(String faxText) {
        enterText(faxNumber, faxText);
        logger.info("CheckoutPage, Fax number entered: {}", faxText);
        return this;
    }

    public CheckoutPage clickOnAddressContinueButton() {
        clickOnElement(addressContinueButton);
        logger.info("CheckoutPage, Address continue button clicked. Next section is Shipping method");
        return this;
    }

    public CheckoutPage fillBillingAddressDetails(String firstNameText, String lastNameText, String emailText, String countryText, String stateText, String cityText, String address1Text, String zibCodeText, String phoneNumberText) {
        enterFirstName(firstNameText)
                .enterLastName(lastNameText)
                .enterEmail(emailText)
                .selectCountry(countryText)
                .selectState(stateText)
                .enterCity(cityText)
                .enterAddress1(address1Text)
                .enterZipCode(zibCodeText)
                .enterPhoneNumber(phoneNumberText);

        logger.info("CheckoutPage, Billing address details filled: {}, {}, {}, {}, {}, {}, {}, {}, {}",
                firstNameText, lastNameText, emailText, countryText, stateText, cityText, address1Text, zibCodeText, phoneNumberText);
        return this;
    }

    public CheckoutPage leaveFieldBlank(String fieldName) {
        By fieldLocator = switch (fieldName.toLowerCase()) {
            case "first name" -> firstName;
            case "last name" -> lastName;
            case "email" -> email;
            case "country" -> country;
            case "state" -> state;
            case "city" -> city;
            case "address 1" -> address1;
            case "zip code" -> zibCode;
            case "phone number" -> phoneNumber;
            default -> throw new IllegalArgumentException("Invalid field name: " + fieldName);
        };
        if (fieldLocator == country) {
            selectDropdownByVisibleText(fieldLocator, "Select country");
        } else if (fieldLocator == state) {
            selectDropdownByVisibleText(fieldLocator, "Select state");
        } else {
            clearText(fieldLocator);
        }

        logger.info("CheckoutPage, Field '{}' left blank.", fieldName);
        return this;
    }

    /*
     * Shipping method section
     */

    public CheckoutPage selectGroundShippingMethod() {
        clickOnElement(groundRadioBtn);
        logger.info("CheckoutPage, Ground shipping method selected.");
        return this;
    }

    public CheckoutPage selectNextDayShippingMethod() {
        clickOnElement(nextDayRadioBtn);
        logger.info("CheckoutPage, Next day shipping method selected.");
        return this;
    }

    public CheckoutPage selectSecondDayShippingMethod() {
        clickOnElement(secondDayRadioBtn);
        logger.info("CheckoutPage, Second day shipping method selected.");
        return this;
    }

    public CheckoutPage clickOnShippingMethodContinueButton() {
        clickOnElement(shippingMethodContinueButton);
        logger.info("Shipping method continue button clicked. Next section is Payment method");
        return this;
    }

    /*
     * Payment method section
     */

    public CheckoutPage selectCheckMoneyOrderPaymentMethod() {
        clickOnElement(moneyOrderRadioBtn);
        logger.info("CheckoutPage, Check or Money Order payment method selected.");
        return this;
    }

    public CheckoutPage selectCreditCardPaymentMethod() {
        clickOnElement(creditCardRadioBtn);
        logger.info("CheckoutPage, Credit Card payment method selected.");
        return this;
    }

    public CheckoutPage clickOnPaymentMethodContinueButton() {
        clickOnElement(paymentMethodContinueButton);
        logger.info("Payment method continue button clicked. Next section is Payment information");
        return this;
    }

    /*
     * Payment information section
     */

    public CheckoutPage selectCreditCardType(String cardType) {
        selectDropdownByVisibleText(creditCardType, cardType);
        logger.info("CheckoutPage, Credit card type selected: {}", cardType);
        return this;
    }

    public CheckoutPage enterCardholderName(String cardholderNameText) {
        enterText(cardholderName, cardholderNameText);
        logger.info("CheckoutPage, Cardholder name entered: {}", cardholderNameText);
        return this;
    }

    public CheckoutPage enterCardNumber(String cardNumberText) {
        enterText(cardNumber, cardNumberText);
        logger.info("CheckoutPage, Card number entered: {}", cardNumberText);
        return this;
    }

    public CheckoutPage selectExpirationDate(String month, String year) {
        selectDropdownByVisibleText(expirationMonth, month);
        selectDropdownByVisibleText(expirationYear, year);
        logger.info("CheckoutPage, Expiration date selected: Month - {}, Year - {}", month, year);
        return this;
    }

    public CheckoutPage enterCardCode(String cardCodeText) {
        enterText(cardCode, cardCodeText);
        logger.info("CheckoutPage, Card code entered: {}", cardCodeText);
        return this;
    }

    public CheckoutPage clickOnPaymentInfoContinueButton() {
        clickOnElement(paymentInfoContinueButton);
        logger.info("Payment information continue button clicked. Next section is Confirm order");
        return this;
    }

    public CheckoutPage fillCreditCardPaymentInformationDetails(String cardType, String cardholderNameText, String cardNumberText, String month, String year, String cardCodeText) {
        selectCreditCardType(cardType)
                .enterCardholderName(cardholderNameText)
                .enterCardNumber(cardNumberText)
                .selectExpirationDate(month, year)
                .enterCardCode(cardCodeText);

        logger.info("CheckoutPage, Credit card payment information filled: {}, {}, {}, {}, {}, {}",
                cardType, cardholderNameText, cardNumberText, month, year, cardCodeText);

        return this;
    }

    /*
     * Confirm order section
     */

    public void clickOnConfirmButton() {
        clickOnElement(confirmButton);
        logger.info("CheckoutPage, Order confirmation button clicked.");
    }

    public boolean isProductInCheckout(String productName) {
        By productLocator = By.xpath(String.format(specificProductName, productName));
        if (isElementDisplayed(productLocator)) {
            logger.info("Product {} is present in the checkout.", productName);
            return true;
        } else {
            logger.info("Product {} is not present in the checkout.", productName);
            return false;
        }
    }

    public String getProductName(String productName) {
        By productLocator = By.xpath(String.format(specificProductName, productName));
        return getText(productLocator);
    }

    public void openProductDetailPage(String productName) {
        By productLocator = By.xpath(String.format(specificProductName, productName));
        clickOnElement(productLocator);
    }

    public List<String> getAllProductsNames() {
        return getElementsText(productsNames);
    }

    public double getTotalCheckoutPrice() {
        String totalPriceText = getText(totalCheckoutPrice).replaceAll("\\$", "").trim();
        return Double.parseDouble(totalPriceText);
    }

    public double getProductSubtotalPrice(String productName) {
        By totalPriceLocator = By.xpath(String.format(totalProductPrice, productName));
        String totalPriceText = getText(totalPriceLocator).replaceAll("\\$", "").trim();
        logger.info("Subtotal price for product {}: {}", productName, totalPriceText);
        return Double.parseDouble(totalPriceText);
    }

    public int getProductQuantity(String productName) {
        By quantityLocator = By.xpath(String.format(productQuantity, productName));
        String quantityText = getText(quantityLocator).trim();
        logger.info("Quantity for product {}: {}", productName, quantityText);
        return Integer.parseInt(quantityText);
    }

    public double getSumOfAllProductsPrice() {
        List<String> productNames = getAllProductsNames();
        double totalSum = 0.0;
        for (String productName : productNames) {
            totalSum += getProductSubtotalPrice(productName);
        }
        logger.info("Total sum of all products in checkout: {}", totalSum);
        return totalSum;
    }

    /*
     * Success checkout page
     */

    public boolean isUserRedirectedToCheckoutCompletePage() {
        if (validateRedirectionByElement(successMessage)) {
            logger.info("CheckoutPage, Redirected to success checkout page.");
            return true;
        } else {
            logger.warn("CheckoutPage, Not redirected to success checkout page.");
            return false;
        }
    }

    public String getCheckoutSuccessMessage() {
        return getText(successMessage);
    }

    //TODO : refactor the string of order number
    public void getOrderNumber() {
        //  String orderNumberText = getText(orderNumber).replaceAll("#", "").trim();
        //  logger.info("Order number retrieved: {}", orderNumberText);
        //return Integer.parseInt(orderNumberText);
    }

    public void clickOnOrderDetailLink() {
        clickOnElement(orderDetailLink);
        logger.info("CheckoutPage, Order detail link clicked.");
    }

    public void clickOnOrderCompleteContinueButton() {
        clickOnElement(orderCompleteContinueButton);
        logger.info("CheckoutPage, Order complete continue button clicked.");
    }

    public boolean isUserRedirectedToHomePage() {
        if (validateRedirectionByElement(homePageWelcomeMessage)) {
            logger.info("CheckoutPage, User is Redirected to home page after order completion.");
            return true;
        } else {
            logger.warn("CheckoutPage, USer is Not redirected to home page after order completion.");
            return false;
        }
    }

    /*
     * Alert messages
     */

    public void acceptAlertOfFieldsValidation() {
        if (isAlertPresent()) {
            acceptAlert();
            logger.info("Alert for fields validation accepted.");
        } else {
            logger.warn("No alert present for fields validation.");
        }
    }

    public boolean isValidationMessagePresentInAlert(String fieldName) {
        if (!isAlertPresent()) {
            logger.warn("Checkout, Billing address, No alert present to check validation message for '{}'.", fieldName);
            return false;
        }

        String alertText = getAlertText();

        // Map of field names to expected messages
        Map<String, String> expectedMessages = Map.of(
                "first name", "First name is required",
                "last name", "Last name is required",
                "email", "Email is required",
                "country", "Country is required",
                "state", "State / province is required",
                "city", "City is required",
                "address 1", "Street address is required",
                "zip code", "Zip / postal code is required",
                "phone number", "Phone is required"
        );

        String normalizedField = fieldName.toLowerCase();
        if (!expectedMessages.containsKey(normalizedField)) {
            logger.warn("Checkout, Billing address, Invalid field name: '{}'.", fieldName);
            return false;
        }

        String expectedMessage = expectedMessages.get(normalizedField);

        if (alertText.contains(expectedMessage)) {
            logger.info("Checkout, Billing address, Validation message for '{}' is present in the alert.", fieldName);
            return true;
        } else {
            logger.warn("Checkout, Billing address, Validation message for '{}' is NOT present in the alert.", fieldName);
            return false;
        }
    }


}
