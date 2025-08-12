package tests.shop;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.shop.RegisterPage;
import utils.WaitUtils;

import static utils.JsonUtils.getValue;

public class RegisterTest extends BaseTest {

    //TODO : add listener, logs, screenshots, allure report.
    //TODO : make Email dynamically generated using timestamp.

    @Test(priority = 1, description = "Register successfully with valid mandatory and optional data")
    public void registerWithValidDataTC() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.navigateToRegisterPage()
                .selectGender("male")
                .enterFirstName(getValue("registerTestData.json", "validRegistration.firstName"))
                .enterLastName(getValue("registerTestData.json", "validRegistration.lastName"))
                .enterEmail(getValue("registerTestData.json", "validRegistration.email"))
                .enterCompany("Test Company")
                .enterPassword("Test@123")
                .enterConfirmPassword("Test@123")
                .clickRegisterButton();

        Assert.assertTrue(registerPage.isRegistrationSuccessMessageDisplayed(getValue("registerTestData.json", "validRegistration.successMessage")),
                "Registration success message is not displayed as expected");

        registerPage.clickRegisterContinueButton();

        Assert.assertTrue(WaitUtils.waitForUrlToContain("https://demo.nopcommerce.com/", 10),
                "URL did not contain expected text after registration");
    }

    @Test(priority = 0, description = "Verify validation messages appear for empty mandatory fields")
    public void verifyMandatoryFieldsValidationsTC() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.navigateToRegisterPage()
                .clickRegisterButton();

        Assert.assertTrue(registerPage.isFirstNameErrorDisplayed(getValue("registerTestData.json", "missingAllMandatory.firstNameErrorMessage")),
                "First name error message is not displayed as expected");
        Assert.assertTrue(registerPage.isLastNameErrorDisplayed(getValue("registerTestData.json", "missingAllMandatory.lastNameErrorMessage")),
                "Last name error message is not displayed as expected");
        Assert.assertTrue(registerPage.isEmailErrorDisplayed(getValue("registerTestData.json", "missingAllMandatory.emailErrorMessage")),
                "Email error message is not displayed as expected");
        Assert.assertTrue(registerPage.isConfirmPasswordErrorDisplayed(getValue("registerTestData.json", "missingAllMandatory.confirmPasswordErrorMessage")),
                "Confirm password error message is not displayed as expected");
    }

    @Test(priority = 2, description = "Verify error message on duplicate email registration")
    public void verifyEmailAlreadyExistsValidationTC() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.navigateToRegisterPage()
                .selectGender("male")
                .enterFirstName(getValue("registerTestData.json", "emailAlreadyExists.firstName"))
                .enterLastName(getValue("registerTestData.json", "emailAlreadyExists.lastName"))
                .enterEmail(getValue("registerTestData.json", "emailAlreadyExists.email"))
                .enterCompany("Test Company")
                .enterPassword(getValue("registerTestData.json", "emailAlreadyExists.password"))
                .enterConfirmPassword(getValue("registerTestData.json", "emailAlreadyExists.confirmPassword"))
                .clickRegisterButton();

        Assert.assertTrue(registerPage.isEmailAlreadyExistsErrorDisplayed(getValue("registerTestData.json", "emailAlreadyExists.existsEmailErrorMessage")),
                "Email already exists error message is not displayed as expected");
    }

    @Test(description = "Verify error message when first name is missing")
    public void verifyMissingFirstNameValidationTC() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.navigateToRegisterPage()
                .selectGender("male")
                .enterLastName(getValue("registerTestData.json", "missingFirstName.lastName"))
                .enterEmail(getValue("registerTestData.json", "missingFirstName.email"))
                .enterCompany("Test Company")
                .enterPassword(getValue("registerTestData.json", "missingFirstName.password"))
                .enterConfirmPassword(getValue("registerTestData.json", "missingFirstName.confirmPassword"))
                .clickRegisterButton();

        Assert.assertTrue(registerPage.isFirstNameErrorDisplayed(getValue("registerTestData.json", "missingFirstName.firstNameErrorMessage")),
                "First name error message is not displayed as expected");
    }

    @Test(description = "Verify error message when last name is missing")
    public void verifyMissingLastNameValidationTC() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.navigateToRegisterPage()
                .selectGender("male")
                .enterFirstName(getValue("registerTestData.json", "missingLastName.firstName"))
                .enterEmail(getValue("registerTestData.json", "missingLastName.email"))
                .enterCompany("Test Company")
                .enterPassword(getValue("registerTestData.json", "missingLastName.password"))
                .enterConfirmPassword(getValue("registerTestData.json", "missingLastName.confirmPassword"))
                .clickRegisterButton();

        Assert.assertTrue(registerPage.isLastNameErrorDisplayed(getValue("registerTestData.json", "missingLastName.lastNameErrorMessage")),
                "Last name error message is not displayed as expected");
    }

    @Test(description = "Verify error message when email is missing")
    public void verifyMissingEmailValidationTC() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.navigateToRegisterPage()
                .selectGender("male")
                .enterFirstName(getValue("registerTestData.json", "missingEmail.firstName"))
                .enterLastName(getValue("registerTestData.json", "missingEmail.lastName"))
                .enterCompany("Test Company")
                .enterPassword(getValue("registerTestData.json", "missingEmail.password"))
                .enterConfirmPassword(getValue("registerTestData.json", "missingEmail.confirmPassword"))
                .clickRegisterButton();

        Assert.assertTrue(registerPage.isEmailErrorDisplayed(getValue("registerTestData.json", "missingEmail.missingEmailErrorMessage")),
                "Email error message is not displayed as expected");
    }

    @Test(description = "Verify email format validation")
    public void verifyEmailFormatValidationTC() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.navigateToRegisterPage()
                .selectGender("male")
                .enterFirstName(getValue("registerTestData.json", "invalidEmailFormat.firstName"))
                .enterLastName(getValue("registerTestData.json", "invalidEmailFormat.lastName"))
                .enterEmail(getValue("registerTestData.json", "invalidEmailFormat.email"))
                .enterCompany("Test Company")
                .enterPassword(getValue("registerTestData.json", "invalidEmailFormat.password"))
                .enterConfirmPassword(getValue("registerTestData.json", "invalidEmailFormat.confirmPassword"))
                .clickRegisterButton();

        Assert.assertTrue(registerPage.isEmailErrorDisplayed(getValue("registerTestData.json", "invalidEmailFormat.emailFormatErrorMessage")),
                "Email format error message is not displayed as expected");
    }

    @Test(description = "Verify password format validation")
    public void verifyPasswordFormatValidationTC() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.navigateToRegisterPage()
                .selectGender("male")
                .enterFirstName(getValue("registerTestData.json", "invalidPasswordFormat.firstName"))
                .enterLastName(getValue("registerTestData.json", "invalidPasswordFormat.lastName"))
                .enterEmail(getValue("registerTestData.json", "invalidPasswordFormat.email"))
                .enterCompany("Test Company")
                .enterPassword(getValue("registerTestData.json", "invalidPasswordFormat.password"))
                .enterConfirmPassword(getValue("registerTestData.json", "invalidPasswordFormat.confirmPassword"))
                .clickRegisterButton();

        Assert.assertTrue(registerPage.isPasswordErrorDisplayed(getValue("registerTestData.json", "invalidPasswordFormat.passwordFormatErrorMessage")),
                "Password format error message is not displayed as expected");
    }

    @Test(description = "Verify confirm password mismatch validation")
    public void verifyConfirmPasswordMismatchValidationTC() {
        RegisterPage registerPage = new RegisterPage();
        registerPage.navigateToRegisterPage()
                .selectGender("male")
                .enterFirstName(getValue("registerTestData.json", "confirmPasswordMismatch.firstName"))
                .enterLastName(getValue("registerTestData.json", "confirmPasswordMismatch.lastName"))
                .enterEmail(getValue("registerTestData.json", "confirmPasswordMismatch.email"))
                .enterCompany("Test Company")
                .enterPassword(getValue("registerTestData.json", "confirmPasswordMismatch.password"))
                .enterConfirmPassword(getValue("registerTestData.json", "confirmPasswordMismatch.confirmPassword"))
                .clickRegisterButton();

        Assert.assertTrue(registerPage.isConfirmPasswordErrorDisplayed(getValue("registerTestData.json", "confirmPasswordMismatch.confirmPasswordErrorMessage")),
                "Confirm password error message is not displayed as expected");


    }
}
