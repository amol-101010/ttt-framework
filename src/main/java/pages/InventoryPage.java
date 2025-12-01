package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;

    // Locators
    By productList = By.className("inventory_item");
    By cartBadge = By.className("shopping_cart_badge");
    By cartLink = By.className("shopping_cart_link");
    By inventoryContainer = By.className("inventory_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method 1: Add product to cart by product name
    public void addProductToCart(String productName) {
        java.util.List<org.openqa.selenium.WebElement> products =
                this.driver.findElements(productList);

        for (org.openqa.selenium.WebElement product : products) {
            if (product.getText().contains(productName)) {
                org.openqa.selenium.WebElement addButton =
                        product.findElement(By.xpath(".//button[contains(text(), 'Add to cart')]"));
                addButton.click();
                break;
            }
        }
    }

    // Method 2: Get cart badge count
    public String getCartBadgeCount() {
        try {
            return this.driver.findElement(cartBadge).getText();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return "0"; // Cart is empty if badge not present
        }
    }

    // Method 3: Navigate to cart
    public CartPage goToCart() {
        this.driver.findElement(cartLink).click();
        return new CartPage(this.driver);
    }

    // Method 4: Verify inventory page loaded
    public boolean isInventoryPageLoaded() {
        return this.driver.findElement(inventoryContainer).isDisplayed();
    }
}
