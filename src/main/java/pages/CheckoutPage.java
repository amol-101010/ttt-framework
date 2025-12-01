package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    // Locators
    By txtFirstName = By.id("first-name");
    By txtLastName = By.id("last-name");
    By txtPostalCode = By.id("postal-code");
    By btnContinue = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method 1: Fill checkout information
    public void fillCheckoutInfo(String firstName, String lastName, String postalCode) {
        this.driver.findElement(txtFirstName).sendKeys(firstName);
        this.driver.findElement(txtLastName).sendKeys(lastName);
        this.driver.findElement(txtPostalCode).sendKeys(postalCode);
    }

    // Method 2: Click continue button
    public CheckoutSummaryPage clickContinue() {
        this.driver.findElement(btnContinue).click();
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
