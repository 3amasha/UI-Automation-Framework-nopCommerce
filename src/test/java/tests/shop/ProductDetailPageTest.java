package tests.shop;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.shop.ProductDetailPage;

import static utils.JsonUtils.getValue;

public class ProductDetailPageTest extends BaseTest {

    /* **************************************************************************************************************
     * <Build your own computer> Test Cases
     ***************************************************************************************************************/

    @Test(priority = 0, description = "Navigate to Computer product detail page")
    public void verifyNavigatingToComputerProductDetailPageTC() {
        ProductDetailPage productDetailPage = new ProductDetailPage();

        productDetailPage
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.computer"));

        Assert.assertTrue(productDetailPage.isUserRedirectedToProductDetailPage(getValue("productsTestData.json", "productsTextName.computerPdpURL"))
                , "User is not redirected to Product Detail page");
    }

    @Test(priority = 1, description = "verify adding computer to cart with all mandatory fields selected")
    public void verifyAddingComputerProductToCartFunctionalityTC() {
        ProductDetailPage productDetailPage = new ProductDetailPage();

        productDetailPage
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.computer"))
                .selectProcessor("2.2 GHz")
                .selectRam("4GB")
                .selectHDD("320GB")
                .selectOS("premium")
                .selectSoftware("Microsoft Office")
                .clickOnAddToCartButtonInPDP();

        Assert.assertTrue(productDetailPage.isBarNotificationDisplayed(), " Bar notification is not displayed");
        Assert.assertEquals(productDetailPage.getBarNotificationText(), getValue("productsTestData.json", "barNotificationText[3]"), " Bar notification text is not as expected");
    }

    @Test(priority = 2, description = "verify adding computer to wishList with all mandatory fields selected")
    public void verifyAddingComputerProductToWishListFunctionalityTC() {
        ProductDetailPage productDetailPage = new ProductDetailPage();

        productDetailPage
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.computer"))
                .selectProcessor("2.5 GHz")
                .selectRam("8GB")
                .selectHDD("400GB")
                .selectOS("home")
                .selectSoftware("Microsoft Office")
                .clickOnAddToWishlistButtonInPDP();

        Assert.assertTrue(productDetailPage.isBarNotificationDisplayed(), " Bar notification is not displayed");
        Assert.assertEquals(productDetailPage.getBarNotificationText(), getValue("productsTestData.json", "barNotificationText[4]"), " Bar notification text is not as expected");
    }

    @Test(priority = 3, description = "verify clicking on 'Email a friend' button in Computer product detail page")
    public void verifyEmailAFriendFunctionalityTC() {
        ProductDetailPage productDetailPage = new ProductDetailPage();

        productDetailPage
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.computer"))
                .selectProcessor("2.5 GHz")
                .selectRam("8GB")
                .selectHDD("400GB")
                .selectOS("home")
                .selectSoftware("Microsoft Office")
                .clickOnEmailAFriendButtonInPDP();

        Assert.assertTrue(productDetailPage.isUserRedirectedToEmailFriendPage(), "User is not redirected to Email a Friend page");
    }

    /* **************************************************************************************************************
     * Gift Card Test Cases.
     ***************************************************************************************************************/

    @Test
    public void verifyVirtualGiftCardWithAllMandatoryTC() {
        ProductDetailPage productDetailPage = new ProductDetailPage();

        productDetailPage
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.virtualGiftCard"))
                .enterRecipientName(getValue("productsTestData.json", "virtualGiftCard.recipientName"))
                .enterRecipientEmail(getValue("productsTestData.json", "virtualGiftCard.recipientEmail"))
                .enterSenderName(getValue("productsTestData.json", "virtualGiftCard.senderName"))
                .enterSenderEmail(getValue("productsTestData.json", "virtualGiftCard.senderEmail"))
                .clickOnAddToCartButtonInPDP();

        Assert.assertTrue(productDetailPage.isBarNotificationDisplayed(), " Bar notification is not displayed");
    }

    /* ****************************************************************************************************************
     * HTC smartphone Test Cases
     ***************************************************************************************************************/
    @Test(description = "Verify adding HTC smartphone to cart from home page")
    public void verifyAddingHTCSmartphoneToCartTC() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        productDetailPage
                .clickOnAddToCartButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"));
        Assert.assertTrue(productDetailPage.isBarNotificationDisplayed(), " Bar notification is not displayed");
    }

    @Test(description = "Verify adding HTC smartphone to wish list from home page")
    public void verifyAddingHTCSmartphoneToWishListTC() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        productDetailPage
                .clickOnAddToWishlistButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"));
        Assert.assertTrue(productDetailPage.isBarNotificationDisplayed(), " Bar notification is not displayed");
    }

    @Test(description = "Verify adding HTC smartphone to compare list from home page")
    public void verifyAddingHTCSmartphoneToCompareListTC() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        productDetailPage
                .clickOnAddToCompareListButton(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"));
        Assert.assertTrue(productDetailPage.isBarNotificationDisplayed(), " Bar notification is not displayed");
    }

    /* *****************************************************************************************************************
     * Apple MacBook Pro Test Cases
     ***************************************************************************************************************/
    @Test
    public void verifyAddingAppleMacBookProToCartTC() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        productDetailPage
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.appleMacBook"))
                .clickOnAddToCartButtonInPDP();

        Assert.assertTrue(productDetailPage.isBarNotificationDisplayed(), " Bar notification is not displayed");
        Assert.assertEquals(productDetailPage.getBarNotificationText(), getValue("productsTestData.json", "barNotificationText[3]"), " Bar notification text is not as expected");


    }

}
