package pages.shop;

import base.BasePage;
import org.openqa.selenium.By;
import utils.WaitUtils;

import static utils.JsonUtils.getValue;

public class ProductDetailPage extends BasePage {

    private final By elementInEmailFriendPage = By.id("PersonalMessage");

    //Home Page Locators
    private final String homePageProductsLink = "//div[@class='product-item'][.//a[text()='%s']]//h2[contains(@class, 'product-title')] /a";
    private final By productName = By.cssSelector(".product-name h1");
    private final By addToWishlistButtonInHome = By.className("add-to-wishlist-button");

    //Product Details Page Locators for a specific product details page
    private final By addToWishlistButtonInPDP = By.cssSelector(".add-to-wishlist .add-to-wishlist-button");
    private final By addToCartButtonInPDP = By.cssSelector("button.button-1.add-to-cart-button");
    private final By addToCompareListButtonInPDP = By.cssSelector(".compare-products .add-to-compare-list-button");
    private final By emailFriendButtonInPDP = By.cssSelector(".email-a-friend-button");
    private final By shareButtonInPDP = By.cssSelector(".st-last img");
    private final By barNotificationInPDP = By.cssSelector(".bar-notification p");
    private final By closeBarNotificationBtn = By.cssSelector("div.bar-notification .close");
    private final By productPriceInPDP = By.cssSelector(".product-price span");

    //Build your own computer Locators in product details page
    private final By processorDropdown = By.cssSelector("dd #product_attribute_1");
    private final By ramDropdown = By.cssSelector("dd #product_attribute_2");

    private final By hdd320GBRadioBtn = By.cssSelector("li #product_attribute_3_6");
    private final By hdd400GBRadioBtn = By.cssSelector("li #product_attribute_3_7");

    private final By osHomeRadioBtn = By.cssSelector("li #product_attribute_4_8");
    private final By osPremiumRadioBtn = By.cssSelector("li #product_attribute_4_9");

    private final By softwareMicrosoftCheckBox = By.cssSelector("li #product_attribute_5_10");
    private final By softwareAcrobatCheckBox = By.cssSelector("li #product_attribute_5_11");
    private final By softwareTotalCheckBox = By.cssSelector("li #product_attribute_5_12");

    //Virtual Gift Card Locators in product details page
    private final By recipientNameGiftCard = By.cssSelector(".giftcard .recipient-name");
    private final By recipientEmailGiftCard = By.cssSelector(".giftcard .recipient-email");
    private final By yourNameGiftCard = By.cssSelector(".giftcard .sender-name");
    private final By yourEmailGiftCard = By.cssSelector(".giftcard .sender-email");
    private final By messageGiftCard = By.cssSelector(".giftcard .message");


    // At any page except product detail page of specific product
    // (Rp)Related products in Product Details Page
    // It is string to make it dynamic in the method to add the order of the product (1: max no of product in page)
    // By locator is created inside the method using this string
    private final String addToCartBtn = "//div[@class='product-item'][.//a[text()='%s']]//button[contains(@class, 'add-to-cart-button')]";
    private final String addToCompareListBtn = "//div[@class='product-item'][.//a[text()='%s']]//button[contains(@class, 'add-to-compare-list-button')]";
    private final String addToWishlistBtn = "//div[@class='product-item'][.//a[text()='%s']]//button[contains(@class, 'add-to-wishlist-button')]";
    // private final By productPrice = By.cssSelector("(//div[@class='add-info'] / div[@class='prices']/span)[1]");
    private final String productPrice = "//div[@class='product-item'][.//a[text()='%s']]//span[contains(@class, 'actual-price')]";

    //login
    private final By loginIcon = By.cssSelector("a.ico-login");
    private final By loginEmail = By.id("Email");
    private final By loginPassword = By.id("Password");
    private final By loginButton = By.cssSelector("div.buttons button.login-button");

    /**
     * Default constructor for ProductDetailPage.
     * Initializes the page with the WebDriver instance from BasePage.
     */
    public ProductDetailPage() {
        super();
    }

    public static void waitUntilNotificationClosed(By notificationLocator) {
        WaitUtils.waitForElementToBeInvisible(notificationLocator);
    }




    /* ***********************************************************************************
     * Home Page
     ************************************************************************************/

    /*
     * Login at first to be able to proceed to checkout page.
     */
    public ProductDetailPage logIn() {
        clickOnElement(loginIcon);
        enterText(loginEmail, "user.valid@example.com");
        enterText(loginPassword, "Test@1234");
        clickOnElement(loginButton);
        return this;
    }

    /* ***********************************************************************************
     * Build your own computer - Product Details Page
     ************************************************************************************/

    /**
     * Opens the product details page from the home page by clicking on the product title link.
     *
     * @param productName The name of the product to open
     * @return WishlistPage instance for method chaining
     */
    public ProductDetailPage openProductDetailsPageFromHome(String productName) {
        By productLink = By.xpath(String.format(homePageProductsLink, productName));
        clickOnElement(productLink);
        return this;
    }

    /**
     * Selects the Processor option based on the provided size.
     *
     * @param processorSize The size of the Processor to select must be ("2.2GHz" or "2.5GHz")
     * @return WishlistPage instance for method chaining
     */
    public ProductDetailPage selectProcessor(String processorSize) {
        if (processorSize.replaceAll(" ", "").trim().equalsIgnoreCase("2.2GHz")) {
            selectDropdownByVisibleText(processorDropdown, getValue("productsTestData.json", "buildYourOwnComputer.processor[0]"));
        } else if (processorSize.replaceAll(" ", "").trim().equalsIgnoreCase("2.5GHz")) {
            selectDropdownByVisibleText(processorDropdown, getValue("productsTestData.json", "buildYourOwnComputer.processor[1]"));
        } else {
            //TODO : add logs "Wrong size, 2.2 GHz selected by default" ... also add logs to each branch in if.
        }
        return this;
    }

    /**
     * Selects the Hard Drive option based on the provided size.
     *
     * @param ramSize The size of the RAM to select must be ("2GB" or "4GB" or "8GB")
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage selectRam(String ramSize) {
        if (ramSize.replaceAll(" ", "").trim().equalsIgnoreCase("2GB")) {
            selectDropdownByVisibleText(ramDropdown, getValue("productsTestData.json", "buildYourOwnComputer.ram[0]"));
        } else if (ramSize.replaceAll(" ", "").trim().equalsIgnoreCase("4GB")) {
            selectDropdownByVisibleText(ramDropdown, getValue("productsTestData.json", "buildYourOwnComputer.ram[1]"));
        } else if (ramSize.replaceAll(" ", "").trim().equalsIgnoreCase("8GB")) {
            selectDropdownByVisibleText(ramDropdown, getValue("productsTestData.json", "buildYourOwnComputer.ram[2]"));
        } else {
            //TODO : add logs "Wrong size, 2GB selected by default" ... also add logs to each branch in if.
            // selectDropdownByVisibleText(ramDropdown, getValue("productsTestData.json", "buildYourOwnComputer.ram[0]"));
        }
        return this;
    }

    /**
     * Selects the Hard Drive option based on the provided size.
     *
     * @param hddSize The size of the Hard Drive to select must be ("320GB" or "400GB")
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage selectHDD(String hddSize) {
        if (hddSize.replaceAll(" ", "").trim().equalsIgnoreCase("320GB")) {
            clickOnElement(hdd320GBRadioBtn);
        } else if (hddSize.replaceAll(" ", "").trim().equalsIgnoreCase("400GB")) {
            clickOnElement(hdd400GBRadioBtn);
        } else {
            //TODO : add logs "Wrong size, 320GB selected by default" ... also add logs to each branch in if.
            //clickOnElement(hdd320GBRadioBtn);
        }
        return this;
    }

    /**
     * Selects the Hard Drive option based on the provided size.
     *
     * @return ProductDetailPage instance for method chaining
     * @osType OS Type, the select must be ("home" or "premium")
     */
    public ProductDetailPage selectOS(String osType) {
        if (osType.trim().equalsIgnoreCase("home")) {
            clickOnElement(osHomeRadioBtn);
        } else if (osType.trim().equalsIgnoreCase("premium")) {
            clickOnElement(osPremiumRadioBtn);
        } else {
            //TODO : add logs "Wrong OS type, Home selected by default" ... also add logs to each branch in if.
            //clickOnElement(osHomeRadioBtn);
        }
        return this;
    }

    /* ***********************************************************************************
     * Product Details Page - For a Specific product - Add to Cart, Wishlist, Compare List, Email a Friend
     ************************************************************************************/

    /**
     * Selects the Software option based on the provided software name.
     *
     * @param softwareName The name of the software to select must be ("Microsoft Office" or "Adobe Acrobat" or "Total Commander")
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage selectSoftware(String softwareName) {
        switch (softwareName.trim().toLowerCase()) {
            case "microsoft office":
                clickOnElement(softwareMicrosoftCheckBox);
                break;
            case "adobe acrobat":
                clickOnElement(softwareAcrobatCheckBox);
                break;
            case "total commander":
                clickOnElement(softwareTotalCheckBox);
                break;
            default:
                //TODO : add logs "Wrong software name, Microsoft Office selected by default" ... also add logs to each branch in switch.
                //clickOnElement(softwareMicrosoftCheckBox);
        }
        return this;
    }

    /**
     * For a specific product details page, Clicks on the "ADD TO CART" button in the product details page.
     * This method is used to add the product to the cart.
     *
     * @return
     */
    public ProductDetailPage clickOnAddToCartButtonInPDP() {
        clickOnElement(addToCartButtonInPDP);
        return this;
    }

    /**
     * For a specific product details page, Clicks on the "Add to Wishlist" button in the product details page.
     * This method is used to add the product to the wishlist.
     */
    public void clickOnAddToWishlistButtonInPDP() {
        clickOnElement(addToWishlistButtonInPDP);
    }

    /**
     * For a specific product details page, Clicks on the "Add to Compare List" button in the product details page.
     * This method is used to add the product to the compare list.
     */
    public void clickOnAddToCompareListButtonInPDP() {
        clickOnElement(addToCompareListButtonInPDP);
    }

    /**
     * Clicks on the "Email a Friend" button in the product details page.
     * This method is used to open the email friend dialog.
     */
    public void clickOnEmailFriendButtonInPDP() {
        clickOnElement(emailFriendButtonInPDP);
    }

    /**
     * Retrieves the name of the  product in the product details page
     *
     * @return The name of the product as a String
     */
    public String getProductNameInPDP() {
        return getText(productName);
    }

    /**
     * Validates if the user is redirected to the "Email a Friend" page.
     * This method checks the current URL against the expected email friend page URL.
     *
     * @return true if the user is redirected to the email friend page, false otherwise
     */
    public boolean isUserRedirectedToEmailFriendPage() {
        return validateRedirectionByElement(elementInEmailFriendPage);
        //return validateRedirectionByUrl(getValue("productsTestData.json", "emailFriend.pageURL"));
    }

    /**
     * Clicks on the "Email a Friend" button in the product details page.
     * This method is used to open the email friend page.
     */
    public void clickOnEmailAFriendButtonInPDP() {
        clickOnElement(emailFriendButtonInPDP);
    }

    /**
     * Validates if the user is redirected to the product detail page.
     * This method checks the current URL against the expected product detail page URL.
     *
     * @param expectedPdpURL The expected product detail page URL
     * @return true if the user is redirected to the product detail page, false otherwise
     */
    public boolean isUserRedirectedToProductDetailPage(String expectedPdpURL) {
        return validateRedirectionByUrl(expectedPdpURL);
    }


    /* ***********************************************************************************
     * Any Page
     ************************************************************************************/

    /**
     * Retrieves the price of specific product in the product details page.
     *
     * @return The price of the product as a String, without the dollar sign
     */
    public double getProductPriceInPDP() {
        String price = getText(productPriceInPDP).replaceAll("\\$", "").trim();
        return Double.parseDouble(price);
    }

    /**
     * Clicks on the "Add to Cart" button for a specific product in any page except product detail page of a specific product.
     * This method is used to add the product to the cart.
     *
     * @param productName The name of the product to add to cart
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage clickOnAddToCartButton(String productName) {
        By addToCartButton = By.xpath(String.format(addToCartBtn, productName));
        clickOnElement(addToCartButton);
        return this;
    }

    /**
     * Retrieves the price of a specific product in any page except product detail page of a specific product.
     *
     * @param productName The name of the product to get the price for
     * @return The price of the product as a String, without the dollar sign
     */
    public double getProductPrice(String productName) {
        By actualPrice = By.xpath(String.format(productPrice, productName));
        String price = getText(actualPrice).replaceAll("\\$", "").trim();
        return Double.parseDouble(price);
    }

    /**
     * Clicks on the "Add to Compare List" button for a specific product in any page except product detail page of a specific product.
     * This method is used to add the product to the compare list.
     *
     * @param productName The name of the product to add to compare list
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage clickOnAddToCompareListButton(String productName) {
        By addToCompareListButton = By.xpath(String.format(addToCompareListBtn, productName));
        clickOnElement(addToCompareListButton);
        return this;
    }

    /**
     * Clicks on the "Add to Wishlist" button for a specific product in any page except product detail page of a specific product.
     * This method is used to add the product to the wishlist.
     *
     * @param productName The name of the product to add to the wishlist
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage clickOnAddToWishlistButton(String productName) {
        By wishListButton = By.xpath(String.format(addToWishlistBtn, productName));
        clickOnElement(wishListButton);
        return this;
    }

    /**
     * Clicks on the "Share" button in the product details page.
     * This method is used to share the product via social media.
     */
    public boolean isBarNotificationDisplayed() {
        return isElementDisplayed(barNotificationInPDP);
    }

    /**
     * Retrieves the text of the bar notification in the product details page.
     *
     * @return The text of the bar notification as a String
     */
    public String getBarNotificationText() {
        return getText(barNotificationInPDP);
    }

    /**
     * Closes the bar notification in the product details page.
     * This method is used to close the bar notification after it is displayed.
     *
     * @return WishlistPage instance for method chaining
     */
    public ProductDetailPage closeBarNotification() {
        clickOnElement(closeBarNotificationBtn);
        waitUntilNotificationClosed(closeBarNotificationBtn);
        return this;
    }

    /* ***********************************************************************************
     * Gift Cart product detail page
     ***********************************************************************************/

    /**
     * Enters the recipient's name in the gift card form.
     *
     * @param recipientName The name of the recipient to enter in the gift card form
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage enterRecipientName(String recipientName) {
        enterText(recipientNameGiftCard, recipientName);
        return this;
    }

    /**
     * Enters the recipient's email address in the gift card form.
     *
     * @param recipientEmail The email address of the sender to enter in the gift card form
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage enterRecipientEmail(String recipientEmail) {
        enterText(recipientEmailGiftCard, recipientEmail);
        return this;
    }

    /**
     * Enters the sender's name in the gift card form.
     *
     * @param yourName The name of the sender to enter in the gift card form.
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage enterSenderName(String yourName) {
        enterText(yourNameGiftCard, yourName);
        return this;
    }

    /**
     * Enters the sender's email address in the gift card form.
     *
     * @param yourEmail The email address of the sender to enter in the gift card form
     * @return ProductDetailPage instance for method chaining
     */
    public ProductDetailPage enterSenderEmail(String yourEmail) {
        enterText(yourEmailGiftCard, yourEmail);
        return this;
    }
}
