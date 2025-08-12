package dataProviders.shop;

import org.testng.annotations.DataProvider;

public class CheckoutDataProvider {
    @DataProvider(name = "mandatoryFields")
    public Object[][] mandatoryFields() {
        return new Object[][]{
                {"First name"},
                {"Last name"},
                {"Email"},
                {"Country"},
                {"State"},
                {"City"},
                {"Address 1"},
                {"Zip code"},
                {"Phone number"}
        };
    }
}
