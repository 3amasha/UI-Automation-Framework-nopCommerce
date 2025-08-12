package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Admin {

    //test
    //changing admin class to test git
    private final By locator = new By() {
        @Override
        public List<WebElement> findElements(SearchContext context) {
            return List.of();
        }

        @Override
        public String toString() {
            return "Admin Locator";
        }
    };
}
