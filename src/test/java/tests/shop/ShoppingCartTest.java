package tests.shop;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.shop.ProductDetailPage;
import pages.shop.ShoppingCartPage;
import pages.shop.WishlistPage;

import static utils.JsonUtils.getValue;

public class ShoppingCartTest extends BaseTest {

    @Test(description = "Verify adding product to cart from home page")
    public void verifyAddingProductToCartFromHomePage() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

        productDetailPage
                .clickOnAddToCartButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .closeBarNotification();
        shoppingCartPage
                .navigateToShoppingCartPage();

        Assert.assertTrue(shoppingCartPage.isProductInCartPage(getValue("productsTestData.json", "productsTextName.smartPhoneHTC")), "Product is not found in shopping cart");
    }

    @Test
    public void verifyAddingProductToCartFromPDP() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

        productDetailPage
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.appleMacBook"))
                .clickOnAddToCartButtonInPDP()
                .closeBarNotification();

        shoppingCartPage
                .navigateToShoppingCartPage();

        Assert.assertTrue(shoppingCartPage.isProductInCartPage(getValue("productsTestData.json", "productsTextName.appleMacBook")), "Product is not found in shopping cart");
    }

    @Test
    public void verifyAddingProductToCartFromWishList() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        WishlistPage wishlistPage = new WishlistPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

        productDetailPage
                .clickOnAddToWishlistButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .closeBarNotification();

        wishlistPage
                .navigateToWishlistPage()
                .clickOnAddToCartButton();

        Assert.assertTrue(shoppingCartPage.isProductInCartPage(getValue("productsTestData.json", "productsTextName.smartPhoneHTC")), "Product is not found in shopping cart");
    }

    @Test
    public void verifyRemovingProductFromCart() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

        productDetailPage
                .clickOnAddToCartButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .closeBarNotification();

        shoppingCartPage
                .navigateToShoppingCartPage()
                .removeItemFromCart(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"));

        Assert.assertFalse(shoppingCartPage.isProductInCartPage(getValue("productsTestData.json", "productsTextName.smartPhoneHTC")), "Product is not removed from Cart");
    }

    @Test(description = "Verify cart total price after removing one product")
    public void verifyCartPageTotalsAfterRemovingOneProduct() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

        //Step: Add two products to the cart
        productDetailPage
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .clickOnAddToCartButtonInPDP()
                .closeBarNotification()
                .clickOnAddToCartButton(getValue("productsTestData.json", "productsTextName.nokiaLumia"))
                .closeBarNotification();

        //Step: Navigate to cart page
        shoppingCartPage.navigateToShoppingCartPage();

        //Step: Get subtotal for remaining,first, product (before removal)
        double expected_firstProductPrice = shoppingCartPage.getProductSubtotalPriceInCart(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"));

        //Step: Remove second product from cart
        shoppingCartPage.removeItemFromCart(getValue("productsTestData.json", "productsTextName.nokiaLumia"));

        //Step: Assert: Verify totals only reflect remaining product
        double actual_totalPrice = shoppingCartPage.getTotalCartPrice();

        Assert.assertEquals(actual_totalPrice, expected_firstProductPrice, "Cart total after removing product should equal subtotal of remaining product");

    }


    @Test(description = "Verify product price in cart matches product price on home page")
    public void verifyProductPrice() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

        productDetailPage
                .clickOnAddToCartButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .closeBarNotification();
        double expectedProductPrice = productDetailPage.getProductPrice(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"));

        shoppingCartPage
                .navigateToShoppingCartPage();
        double actualProductPrice = shoppingCartPage.getProductSubtotalPriceInCart(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"));

        Assert.assertEquals(actualProductPrice, expectedProductPrice, "Product price in cart does not match expected price from home page");
    }

    @Test
    public void verifyCartPageTotalsAfterAddingTwoProducts() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();

        //Step: Add two products to the cart
        productDetailPage
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .clickOnAddToCartButtonInPDP()
                .closeBarNotification()
                .clickOnAddToCartButton(getValue("productsTestData.json", "productsTextName.nokiaLumia"))
                .closeBarNotification();

        //Step: Navigate to cart page
        shoppingCartPage.navigateToShoppingCartPage();

        //Step: Get subtotal for first product
        double expected_firstProductPrice = shoppingCartPage.getProductSubtotalPriceInCart(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"));

        //Step: Get subtotal for second product
        double expected_secondProductPrice = shoppingCartPage.getProductSubtotalPriceInCart(getValue("productsTestData.json", "productsTextName.nokiaLumia"));

        //Step: Assert: Verify totals reflect both products
        double actual_totalPrice = shoppingCartPage.getTotalCartPrice();
        double expected_totalPrice = expected_firstProductPrice + expected_secondProductPrice;

        Assert.assertEquals(actual_totalPrice, expected_totalPrice, "Cart total should equal sum of both products' subtotals");
    }


}
