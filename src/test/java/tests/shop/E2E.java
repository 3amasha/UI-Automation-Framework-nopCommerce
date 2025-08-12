package tests.shop;

import base.BaseTest;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.shop.CheckoutPage;
import pages.shop.ProductDetailPage;
import pages.shop.ShoppingCartPage;

import static utils.JsonUtils.getValue;

public class E2E extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutTest.class);

    @BeforeMethod
    @Description("Precondition for Checkout Tests: Login-> Add products to cart and navigate to checkout page")
    public void checkoutTestPrecondition() {
        ProductDetailPage productDetailPage = new ProductDetailPage();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        // Initialize SoftAssert
        softAssert = new SoftAssert();

        //Step: Add two products to Cart
        productDetailPage
                .logIn()
                .openProductDetailsPageFromHome(getValue("productsTestData.json", "productsTextName.smartPhoneHTC"))
                .clickOnAddToCartButtonInPDP()
                .closeBarNotification()
                .clickOnAddToCartButton(getValue("productsTestData.json", "productsTextName.nokiaLumia"))
                .closeBarNotification();

        //Step: Navigate to Shopping Cart page -> click on agree to terms -> click on checkout button
        shoppingCartPage
                .navigateToShoppingCartPage()
                .agreeToTerms()
                .clickOnCheckoutButton();
    }

    @AfterMethod
    public void endCheckoutTest() {
        // Assert all soft assertions
        if (softAssert != null) {
            softAssert.assertAll();
        }
    }

    @Test
    public void testEnd2EndWithCheckMoneyOrder() {
        CheckoutPage checkoutPage = new CheckoutPage();
        // Load billing test data once
        String firstName = getValue("checkoutTestData.json", "billingAddress.firstName");
        String lastName = getValue("checkoutTestData.json", "billingAddress.lastName");
        String email = getValue("checkoutTestData.json", "billingAddress.email");
        String country = getValue("checkoutTestData.json", "billingAddress.country");
        String state = getValue("checkoutTestData.json", "billingAddress.stateProvince");
        String city = getValue("checkoutTestData.json", "billingAddress.city");
        String address1 = getValue("checkoutTestData.json", "billingAddress.address1");
        String zip = getValue("checkoutTestData.json", "billingAddress.zipPostalCode");
        String phone = getValue("checkoutTestData.json", "billingAddress.phoneNumber");

        //Step: Fill in all mandatory fields and click on continue button
        checkoutPage
                .selectToFillNewBillingAddress()
                .fillBillingAddressDetails(firstName, lastName, email, country, state, city, address1, zip, phone)
                .clickOnAddressContinueButton()
                .selectGroundShippingMethod()
                .clickOnShippingMethodContinueButton()
                .selectCheckMoneyOrderPaymentMethod() //select Check Money Order Payment Method
                .clickOnPaymentMethodContinueButton()
                .clickOnPaymentInfoContinueButton()
                .clickOnConfirmButton();

        //Assert: Verify Redirection and success message
        Assert.assertTrue(checkoutPage.isUserRedirectedToCheckoutCompletePage());
        Assert.assertEquals(checkoutPage.getCheckoutSuccessMessage(),
                getValue("checkoutTestData.json", "completeOrder.successMessage"),
                "Checkout success message is not displayed as expected");

        //Assert: Verify redirection to home page after continue button click
        checkoutPage.clickOnOrderCompleteContinueButton();
        Assert.assertTrue(checkoutPage.isUserRedirectedToHomePage(), "User is not redirected to home page after clicking continue button");

    }


}
