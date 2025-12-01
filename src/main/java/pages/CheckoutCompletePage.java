package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompletePage {
    WebDriver driver;

    // Locators
    By thankYouMessage = By.className("complete-header");
    By orderCompleteText = By.className("complete-text");
    By backHomeButton = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    // Method 1: Get thank you message
    public String getThankYouMessage() {
        return this.driver.findElement(thankYouMessage).getText();
    }

    // Method 2: Get order complete text
    public String getOrderCompleteText() {
        return this.driver.findElement(orderCompleteText).getText();
    }

    // Method 3: Verify order completion page loaded
    public boolean isOrderCompleted() {
        return this.driver.findElement(thankYouMessage).isDisplayed() &&
                this.driver.findElement(orderCompleteText).isDisplayed();
    }

    // Method 4: Click back to products button
    public InventoryPage clickBackToProducts() {
        this.driver.findElement(backHomeButton).click();
        return new InventoryPage(this.driver);
    }
}
