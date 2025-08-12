package pages.shop;

import base.BasePage;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static pages.shop.ProductDetailPage.waitUntilNotificationClosed;

public class ShoppingCartPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ShoppingCartPage.class);

    private final By shoppingCartIcon = By.cssSelector("a.ico-cart");
    private final String specificProductText = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='product']//a";
    private final String removeBtn = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='remove-from-cart']//button";
    private final String singleProductPrice = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='unit-price']//span";
    private final String totalProductPrice = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='subtotal']//span";
    private final String productQuantity = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='quantity']//div[@class='product-quantity']//input";
    private final String quantityUp = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='quantity']//div[@class='product-quantity']//div[contains(@class, 'up')]";
    private final String quantityDown = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='quantity']//div[@class='product-quantity']//div[contains(@class, 'down')]";
    private final String editProductBtn = "//tr[td[@class='product']//a[normalize-space(text())='%s']]//td[@class='product']//div//a";


    private final By continueShoppingButton = By.cssSelector("//button[contains(@class, 'continue-shopping-button')]");
    private final By estimateShoppingButton = By.cssSelector("//a[contains(@class, 'estimate-shipping-button')]");
    private final By discountCode = By.cssSelector("input#discountcouponcode");
    private final By giftCards = By.cssSelector("input#giftcardcouponcode");
    private final By termsCheckBox = By.cssSelector("input#termsofservice");
    private final By checkoutBtn = By.cssSelector("button#checkout");
    private final By totalCartPrice = By.cssSelector("span.value-summary strong");
    private final By barNotification = By.cssSelector(".bar-notification p");
    private final By closeBarNotificationBtn = By.cssSelector("div.bar-notification .close");


    public ShoppingCartPage() {
        super(); // Ensures correct driver from ThreadLocal
    }


    /**
     * Navigates to the shopping cart page by clicking on the shopping cart icon in the header.
     *
     * @return ShoppingCartPage instance for method chaining.
     */
    public ShoppingCartPage navigateToShoppingCartPage() {
        clickOnElement(shoppingCartIcon);
        return this;
    }

    /**
     * Checks if a product is present in the shopping cart page by its name.
     *
     * @param productName The name of the product to check.
     * @return true if the product is displayed, false otherwise.
     */
    public boolean isProductInCartPage(String productName) {
        By productLocator = By.xpath(String.format(specificProductText, productName));
        return isElementDisplayed(productLocator);
    }

    /**
     * Navigates to the product detail page from the shopping cart page by clicking on the product name.
     *
     * @param productName The name of the product to navigate to its detail page.
     */
    public void navigateToProductDetailPage(String productName) {
        By productLocator = By.xpath(String.format(specificProductText, productName));
        clickOnElement(productLocator);
    }

    /**
     * Removes an item from the shopping cart by its product name.
     *
     * @param productName The name of the product to remove from the cart.
     */
    public void removeItemFromCart(String productName) {
        By removeButton = By.xpath(String.format(removeBtn, productName));
        clickOnElement(removeButton);
    }

    public double getProductPriceInCart(String productName) {
        By priceLocator = By.xpath(String.format(singleProductPrice, productName));
        String price = getText(priceLocator).replaceAll("\\$", "").trim();
        return Double.parseDouble(price);
    }

    public double getProductSubtotalPriceInCart(String productName) {
        By totalPriceLocator = By.xpath(String.format(totalProductPrice, productName));

        String totalPrice = getText(totalPriceLocator).replaceAll("\\$", "").trim();
        return Double.parseDouble(totalPrice);
    }

    public double getTotalCartPrice() {
        String totCartPrice = getText(totalCartPrice).replaceAll("\\$", "").trim();
        return Double.parseDouble(totCartPrice);
    }

    public int getProductQuantity(String productName) {
        By quantityLocator = By.xpath(String.format(productQuantity, productName));
        String quantity = getAttribute(quantityLocator, "value").trim();
        return Integer.parseInt(quantity);
    }

    public void increaseProductQuantity(String productName) {
        By quantityUpLocator = By.xpath(String.format(quantityUp, productName));
        clickOnElement(quantityUpLocator);
    }

    public void decreaseProductQuantity(String productName) {
        By quantityDownLocator = By.xpath(String.format(quantityDown, productName));
        clickOnElement(quantityDownLocator);
    }

    public void clickOnContinueShoppingButton() {
        clickOnElement(continueShoppingButton);
    }

    public void clickOnEstimateShoppingButton() {
        clickOnElement(estimateShoppingButton);
    }

    public void enterDiscountCode(String code) {
        enterText(discountCode, code);
    }

    public void enterGiftCardCode(String code) {
        enterText(giftCards, code);
    }

    public ShoppingCartPage agreeToTerms() {
        clickOnElement(termsCheckBox);
        return this;
    }

    public CheckoutPage clickOnCheckoutButton() {
        clickOnElement(checkoutBtn);
        return new CheckoutPage();
    }


    public void clickOnEditProductButton(String productName) {
        By editButton = By.xpath(String.format(editProductBtn, productName));
        clickOnElement(editButton);
    }

    /**
     * Clicks on the "Share" button in the product details page.
     * This method is used to share the product via social media.
     */
    public boolean isBarNotificationDisplayed() {
        return isElementDisplayed(barNotification);
    }

    /**
     * Retrieves the text of the bar notification in the product details page.
     *
     * @return The text of the bar notification as a String
     */
    public String getBarNotificationText() {
        return getText(barNotification);
    }

    public ShoppingCartPage closeBarNotification() {
        clickOnElement(closeBarNotificationBtn);
        waitUntilNotificationClosed(closeBarNotificationBtn);
        return this;
    }


}
