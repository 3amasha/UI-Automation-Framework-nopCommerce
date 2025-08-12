package pages.shop;

import base.BasePage;
import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

    private final By registerButton = By.className("ico-register");
    private final By maleRadioButton = By.id("gender-male");
    private final By femaleRadioButton = By.id("gender-female");
    private final By firstName = By.id("FirstName");
    private final By lastName = By.id("LastName");
    private final By email = By.id("Email");
    private final By company = By.id("Company");
    private final By password = By.id("Password");
    private final By confirmPassword = By.id("ConfirmPassword");
    private final By registerButtonSubmit = By.id("register-button");
    private final By firstNameError = By.id("FirstName-error");
    private final By lastNameError = By.id("LastName-error");
    private final By emailError = By.id("Email-error");
    private final By passwordError = By.id("Password-error");
    private final By confirmPasswordError = By.id("ConfirmPassword-error");
    private final By registrationSuccessMessage = By.cssSelector("div.result");
    private final By registerContinueButton = By.className("register-continue-button");
    private final By existingEmailError = By.cssSelector("form li");

    public RegisterPage() {
        super(); // ✅ Ensures correct driver from ThreadLocal
    }

    /**
     * Clicks on the register link in home page to navigate to the registration page.
     *
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage navigateToRegisterPage() {
        clickOnElement(registerButton);
        return this;
    }

    /**
     * Selects the gender for the registration form.
     *
     * @param gender (male or female)
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage selectGender(String gender) {
        if (gender.equalsIgnoreCase("female")) {
            clickOnElement(femaleRadioButton);
        } else {
            clickOnElement(maleRadioButton);
        }
        return this;
    }

    /**
     * Enters the first name in the registration form.
     *
     * @param firstNameText :Text First name to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterFirstName(String firstNameText) {
        enterText(firstName, firstNameText);
        return this;
    }

    /**
     * Enters the last name in the registration form.
     *
     * @param lastNameText :Text Last name to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterLastName(String lastNameText) {
        enterText(lastName, lastNameText);
        return this;
    }

    /**
     * Enters the email in the registration form.
     *
     * @param emailText :Text Email to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterEmail(String emailText) {
        enterText(email, emailText);
        return this;
    }

    /**
     * Enters the company name in the registration form.
     *
     * @param companyText :Text Company name to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterCompany(String companyText) {
        enterText(company, companyText);
        return this;
    }

    /**
     * Enters the password in the registration form.
     *
     * @param passwordText :Text Password to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterPassword(String passwordText) {
        enterText(password, passwordText);
        return this;
    }

    /**
     * Enters the confirm password in the registration form.
     *
     * @param confirmPasswordText :Text Confirm password to enter
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage enterConfirmPassword(String confirmPasswordText) {
        enterText(confirmPassword, confirmPasswordText);
        return this;
    }

    /**
     * Clicks on the register button to submit the registration form.
     *
     * @return RegisterPage instance for method chaining
     */
    public RegisterPage clickRegisterButton() {
        clickOnElement(registerButtonSubmit);
        return this;
    }

    /**
     * Checks if the first name error message is displayed.
     *
     * @param errorText Expected error text to match
     * @return true if the error message matches, false otherwise
     */
    public boolean isFirstNameErrorDisplayed(String errorText) {
        return getText(firstNameError).matches(errorText);
    }

    /**
     * Checks if the last name error message is displayed.
     *
     * @param errorText Expected error text to match
     * @return true if the error message matches, false otherwise
     */
    public boolean isLastNameErrorDisplayed(String errorText) {
        return getText(lastNameError).matches(errorText);
    }

    /**
     * Checks if the email error message is displayed.
     *
     * @param errorText Expected error text to match
     * @return true if the error message matches, false otherwise
     */
    public boolean isEmailErrorDisplayed(String errorText) {
        return getText(emailError).matches(errorText);
    }

    /**
     * Checks if the password error message is displayed.
     *
     * @param errorText Expected error text to match
     * @return true if the error message matches, false otherwise
     */
    public boolean isPasswordErrorDisplayed(String errorText) {
        return getText(passwordError).matches(errorText);
    }

    /**
     * Checks if the confirm password error message is displayed.
     *
     * @param errorText Expected error text to match
     * @return true if the error message matches, false otherwise
     */
    public boolean isConfirmPasswordErrorDisplayed(String errorText) {
        return getText(confirmPasswordError).matches(errorText);
    }

    /**
     * Checks if the registration success message is displayed.
     *
     * @param successMessage Expected success message to match
     * @return true if the success message matches, false otherwise
     */
    public boolean isRegistrationSuccessMessageDisplayed(String successMessage) {
        return getText(registrationSuccessMessage).equals(successMessage);
    }

    /**
     * Clicks on the continue button after successful registration.
     */
    public void clickRegisterContinueButton() {
        clickOnElement(registerContinueButton);
    }

    /**
     * Checks if the email already exists error message is displayed.
     *
     * @param errorText Expected error text to match
     * @return true if the error message matches, false otherwise
     */
    public boolean isEmailAlreadyExistsErrorDisplayed(String errorText) {
        return getText(existingEmailError).matches(errorText);
    }


}
