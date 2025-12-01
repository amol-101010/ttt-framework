package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage extends BasePage {
    WebDriver driver;

    // Locators
    By productList = By.className("inventory_item");
    By cartBadge = By.className("shopping_cart_badge");
    By cartLink = By.className("shopping_cart_link");
    By inventoryContainer = By.className("inventory_container");

    public InventoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Method 1: Add product to cart by product name
    public void addProductToCart(String productName) {
       List<WebElement> products =
                this.driver.findElements(productList);

        for (WebElement product : products) {
            if (product.getText().contains(productName)) {
                WebElement addButton =
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
        } catch (NoSuchElementException e) {
            return "0"; // Cart is empty if badge not present
        }
    }

    // Method 3: Navigate to cart
    public CartPage goToCart() {
        clickElement(cartLink);
        return new CartPage(this.driver);
    }

    // Method 4: Verify inventory page loaded
    public boolean isInventoryPageLoaded() {
        return this.driver.findElement(inventoryContainer).isDisplayed();
    }
}
