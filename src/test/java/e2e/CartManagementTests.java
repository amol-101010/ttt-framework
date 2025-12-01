package e2e;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartManagementTests extends BaseTest {

    @Test(description = "TC_E2E_003: Add and remove items from cart workflow")
    public void TC_E2E_003_AddAndRemoveItemsFromCart() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.doLogin("standard_user", "secret_sauce");

        // Step 2: Verify inventory page loaded
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded(), "Inventory page should be loaded");

        // Step 3: Add multiple products to cart
        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bike Light");
        inventoryPage.addProductToCart("Sauce Labs Fleece Jacket");

        // Step 4: Verify cart badge shows 3 items
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "3", "Cart should have 3 items");

        // Step 5: Navigate to cart
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");
        Assert.assertEquals(cartPage.getCartItemCount(), 3, "Cart should contain 3 items");

        // Step 6: Remove one item from cart
        cartPage.removeProductFromCart("Sauce Labs Bike Light");
        Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart should contain 2 items after removal");

        // Step 7: Remove another item from cart
        cartPage.removeProductFromCart("Sauce Labs Fleece Jacket");
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should contain 1 item after second removal");

        // Step 8: Verify final cart state
        Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "Final cart should contain only 1 item (Sauce Labs Backpack)");
    }

    @Test(description = "TC_E2E_004: Add items to cart and verify cart persistence")
    public void TC_E2E_004_VerifyCartPersistenceAcrossPages() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.doLogin("standard_user", "secret_sauce");

        // Step 2: Add products to cart from inventory page
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded(), "Inventory page should be loaded");

        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Onesie");

        // Step 3: Verify cart badge count on inventory page
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "2",
                "Cart badge should show 2 items on inventory page");

        // Step 4: Navigate to cart
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");

        // Step 5: Verify cart items count on cart page
        Assert.assertEquals(cartPage.getCartItemCount(), 2,
                "Cart should persist 2 items on cart page");

        // Step 6: Navigate back to inventory using browser back
        getDriver().navigate().back();

        // Step 7: Verify we're back on inventory page
        InventoryPage inventoryPageAgain = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPageAgain.isInventoryPageLoaded(),
                "Should be back on inventory page");

        // Step 8: Verify cart badge still shows 2 items after navigation
        Assert.assertEquals(inventoryPageAgain.getCartBadgeCount(), "2",
                "Cart badge should still show 2 items after returning to inventory page");

        // Step 9: Add one more product
        inventoryPageAgain.addProductToCart("Test.allTheThings() T-Shirt (Red)");

        // Step 10: Verify cart badge updated to 3 items
        Assert.assertEquals(inventoryPageAgain.getCartBadgeCount(), "3",
                "Cart badge should now show 3 items after adding another product");
    }
}