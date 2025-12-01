package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutSummaryPage {
    WebDriver driver;

    // Locators
    By cartItems = By.className("cart_item");
    By subtotalLabel = By.className("summary_subtotal_label");
    By taxLabel = By.className("summary_tax_label");
    By totalLabel = By.className("summary_total_label");
    By btnFinish = By.id("finish");

    public CheckoutSummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method 1: Get number of items in order summary
    public int getOrderItemCount() {
        return this.driver.findElements(cartItems).size();
    }

    // Method 2: Get order total price
    public String getOrderTotal() {
        return this.driver.findElement(totalLabel).getText();
    }

    // Method 3: Click finish button
    public CheckoutCompletePage clickFinish() {
        this.driver.findElement(btnFinish).click();
        return new CheckoutCompletePage(this.driver);
    }

    // Method 4: Verify summary page loaded with items
    public boolean isCheckoutSummaryLoaded() {
        return this.driver.findElements(cartItems).size() > 0;
    }
}
