package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    WebDriver driver;

    // Locators
    By cartItems = By.className("cart_item");
    By checkoutButton = By.id("checkout");
    By removeButton = By.className("btn_secondary");
    By cartContainer = By.className("cart_list");

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Method 1: Get number of items in cart
    public int getCartItemCount() {
        return this.driver.findElements(cartItems).size();
    }

    // Method 2: Remove product from cart by name
    public void removeProductFromCart(String productName) {
        java.util.List<org.openqa.selenium.WebElement> items =
                this.driver.findElements(cartItems);

        for (org.openqa.selenium.WebElement item : items) {
            if (item.getText().contains(productName)) {
                org.openqa.selenium.WebElement removeBtn =
                        item.findElement(By.className("btn_secondary"));
                removeBtn.click();
                break;
            }
        }
    }

    // Method 3: Click checkout button
    public CheckoutPage proceedToCheckout() {
        clickElement(checkoutButton);
        return new CheckoutPage(this.driver);
    }

    // Method 4: Verify cart page loaded
    public boolean isCartPageLoaded() {
        return this.driver.findElement(cartContainer).isDisplayed();
    }
}
