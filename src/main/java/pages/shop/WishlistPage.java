package pages.shop;

import base.BasePage;
import org.openqa.selenium.By;

import static pages.shop.ProductDetailPage.waitUntilNotificationClosed;

/**
 * WishlistPage class represents the wishlist page in the shopping application.
 * It extends BasePage to inherit common page functionalities.
 * <p>
 * Pre-conditions: Customer must be logged.
 * Flow: -The flow starts from the home page, where the customer can add items to the wishlist.
 * -The flow starts after the customer has logged in and navigated to the Home page.
 */
public class WishlistPage extends BasePage {

    private final By wishlistIcon = By.cssSelector("a.ico-wishlist");
    private final String removeWish = "//td[@class='product']/a[text()='%s']/ancestor::tr//button[@class='remove-btn']";
    private final By wishlistQuantity = By.cssSelector("span.wishlist-qty");
    private final String productText = "//td[@class='product']/a[text()='";
    private final By barNotification = By.cssSelector(".bar-notification p");
    private final String addToCartCheckbox = "//td[@class='product']/a[text()='%s']/preceding::td[@class='add-to-cart']//input";
    private final By addToCartButton = By.cssSelector("button.wishlist-add-to-cart-button");
    private final By closeBarNotificationBtn = By.cssSelector("div.bar-notification .close");


    public WishlistPage() {
        super(); // Ensures correct driver from ThreadLocal
    }


    /**
     * Navigates to the wishlist page by clicking on the wishlist icon in the header.
     *
     * @return WishlistPage instance for method chaining.
     */
    public WishlistPage navigateToWishlistPage() {
        clickOnElement(wishlistIcon);
        return this;
    }

    /**
     * Removes an item from the wishlist by its name.
     *
     * @param productName The name of the product to remove from the wishlist.
     */
    public void removeItemFromWishlistByName(String productName) {
        By removeButton = By.xpath(String.format(removeWish, productName));
        clickOnElement(removeButton);
    }

    /**
     * Retrieves the quantity of items in the wishlist.
     *
     * @return The text content of the wishlist quantity element.
     */
    public int getWishlistQuantity() {
        return Integer.parseInt(getText(wishlistQuantity));
    }

    /**
     * Checks if a product is present in the wishlist by its name.
     *
     * @param productName The name of the product to check in the wishlist.
     * @return true if the product is found in the wishlist, false otherwise.
     */
    public boolean isProductInWishlist(String productName) {
        By productLocator = By.xpath(productText + productName + "']");
        return isElementDisplayed(productLocator);
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

    public void closeBarNotification() {
        clickOnElement(closeBarNotificationBtn);
        waitUntilNotificationClosed(closeBarNotificationBtn);
    }

    /**
     * Clicks on the "Add to Cart" checkbox for a specific product in the wishlist by its name.
     *
     * @param productName The name of the product to add to cart.
     * @return WishlistPage instance for method chaining.
     */
    public WishlistPage clickOnAddToCartCheckboxByName(String productName) {
        By addToCartCheckboxLocator = By.xpath(String.format(addToCartCheckbox, productName));
        clickOnElement(addToCartCheckboxLocator);
        return this;
    }

    /**
     * Click on Add To Cart Button in WishList page and redirect to Shopping Cart Page by default
     * Note: No need to use method navigateToShoppingCartPage()
     *
     * @return ShoppingCartPage
     */
    public ShoppingCartPage clickOnAddToCartButton() {
        clickOnElement(addToCartButton);
        return new ShoppingCartPage();
    }

    public boolean isUserRedirectedToCartPage(String expectedPdpURL) {
        return validateRedirectionByUrl(expectedPdpURL);
    }

}
