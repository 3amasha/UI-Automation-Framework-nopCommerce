package tests.shop;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.shop.ProductDetailPage;
import pages.shop.WishlistPage;

import static utils.JsonUtils.getValue;

public class WishlistTest extends BaseTest {
    //TODO : add listener, logs, screenshots, allure report.
    //TODO : Complete flow from Login

    @Test(description = "Verify adding product to wishlist")
    public void verifyAddingProductToWishlist() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        WishlistPage wishlistPage = new WishlistPage();

        productDetailPage
                .clickOnAddToWishlistButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .closeBarNotification();
        wishlistPage.navigateToWishlistPage();

        Assert.assertTrue(wishlistPage.isProductInWishlist(getValue("wishListTestData.json", "productsText.smartPhoneHTC")), "Product is not found in wishlist");
    }

    @Test(description = "Verify removing product from wishlist")
    public void verifyRemovingProductFromWishlist() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        WishlistPage wishlistPage = new WishlistPage();

        productDetailPage
                .clickOnAddToWishlistButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .closeBarNotification();
        wishlistPage
                .navigateToWishlistPage()
                .removeItemFromWishlistByName(getValue("wishListTestData.json", "productsText.smartPhoneHTC"));

        Assert.assertFalse(wishlistPage.isProductInWishlist(getValue("wishListTestData.json", "productsText.smartPhoneHTC")), "Product is not removed from wishlist");
    }

    @Test(description = "verify Clicking Add To Cart Button Without Selecting ant Product")
    public void verifyClickingAddToCartWithoutSelectingProduct() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        WishlistPage wishlistPage = new WishlistPage();

        productDetailPage.clickOnAddToWishlistButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"));

        Assert.assertTrue(productDetailPage.isBarNotificationDisplayed(), " Bar notification is not displayed");
        Assert.assertEquals(productDetailPage.getBarNotificationText(), getValue("productsTestData.json", "barNotificationText[4]"), " Bar notification text is not add to wishlist");

        productDetailPage.closeBarNotification();

        wishlistPage
                .navigateToWishlistPage()
                .clickOnAddToCartButton();

        Assert.assertTrue(wishlistPage.isBarNotificationDisplayed(), " Bar notification is not displayed");
        Assert.assertEquals(wishlistPage.getBarNotificationText(), getValue("wishListTestData.json", "barNotificationText.noProductsSelected"), " Bar notification text is not No products selected");

    }

    @Test(description = "Verify adding product to cart from wishlist page")
    public void verifyAddingProductToCartFromWishListPage() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        WishlistPage wishlistPage = new WishlistPage();

        productDetailPage
                .clickOnAddToWishlistButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .closeBarNotification();
        wishlistPage
                .navigateToWishlistPage()
                .clickOnAddToCartCheckboxByName(getValue("wishListTestData.json", "productsText.smartPhoneHTC"))
                .clickOnAddToCartButton();

        Assert.assertTrue(wishlistPage.isUserRedirectedToCartPage(getValue("wishListTestData.json", "cartPageURL")), "User is not redirected to cart page after adding product to cart from wishlist");
    }

    @Test(description = "Verify product is removed from wishlist after adding to cart")
    public void verifyProductRemovedFromWishlistAfterAddingToCart() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        WishlistPage wishlistPage = new WishlistPage();

        productDetailPage
                .clickOnAddToWishlistButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .closeBarNotification();
        wishlistPage
                .navigateToWishlistPage()
                .clickOnAddToCartCheckboxByName(getValue("wishListTestData.json", "productsText.smartPhoneHTC"))
                .clickOnAddToCartButton();
        wishlistPage
                .navigateToWishlistPage()
                .isProductInWishlist(getValue("wishListTestData.json", "productsText.smartPhoneHTC"));

        Assert.assertFalse(wishlistPage.isProductInWishlist(getValue("wishListTestData.json", "productsText.smartPhoneHTC")), "Product is not removed from wishlist");
    }


}



