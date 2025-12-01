package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    WebDriver driver;

    // Locators
    By txtFirstName = By.id("first-name");
    By txtLastName = By.id("last-name");
    By txtPostalCode = By.id("postal-code");
    By btnContinue = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Method 1: Fill checkout information
    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        clearTextAndType(txtFirstName, firstName);
        clearTextAndType(txtLastName, lastName);
        clearTextAndType(txtPostalCode, postalCode);
    }

    // Method 2: Click continue button
    public CheckoutSummaryPage clickContinue() {
        clickElement(btnContinue);
        return new CheckoutSummaryPage(this.driver);
    }

    // Method 3: Get error message if validation fails
    public String getErrorMessage() {
        try {
            return this.driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return ""; // No error message
        }
    }

    // Method 4: Fill and continue (combined for convenience)
    public CheckoutSummaryPage fillAndContinue(String firstName, String lastName, String postalCode) {
        fillCheckoutInfo(firstName, lastName, postalCode);
        return clickContinue();
    }
}
