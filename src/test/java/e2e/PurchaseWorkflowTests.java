package e2e;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class PurchaseWorkflowTests extends BaseTest {

    @Test(description = "TC_E2E_001: Complete single product purchase workflow")
    public void TC_E2E_001_CompleteSingleProductPurchase() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.doLogin("standard_user", "secret_sauce");

        // Step 2: Add product to cart
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded(), "Inventory page should be loaded");
        inventoryPage.addProductToCart("Sauce Labs Backpack");

        // Step 3: Verify cart badge count
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1", "Cart should have 1 item");

        // Step 4: Go to cart
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");
        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart should contain 1 item");

        // Step 5: Proceed to checkout
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();

        // Step 6: Fill checkout information
        CheckoutSummaryPage summaryPage = checkoutPage.fillAndContinue("John", "Doe", "12345");

        // Step 7: Verify order summary
        Assert.assertTrue(summaryPage.isCheckoutSummaryLoaded(), "Checkout summary should be loaded");
        Assert.assertEquals(summaryPage.getOrderItemCount(), 1, "Order summary should have 1 item");

        // Step 8: Complete purchase
        CheckoutCompletePage completePage = summaryPage.clickFinish();

        // Step 9: Verify order completion
        Assert.assertTrue(completePage.isOrderCompleted(), "Order should be completed");
        Assert.assertEquals(completePage.getThankYouMessage(), "Thank you for your order!",
                "Thank you message should be displayed");
    }

    @Test(description = "TC_E2E_002: Complete multiple products purchase workflow")
    public void TC_E2E_002_CompleteMultipleProductsPurchase() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.doLogin("standard_user", "secret_sauce");

        // Step 2: Add multiple products to cart
        InventoryPage inventoryPage = new InventoryPage(getDriver());
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded(), "Inventory page should be loaded");

        inventoryPage.addProductToCart("Sauce Labs Backpack");
        inventoryPage.addProductToCart("Sauce Labs Bike Light");
        inventoryPage.addProductToCart("Sauce Labs Bolt T-Shirt");

        // Step 3: Verify cart badge count
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "3", "Cart should have 3 items");

        // Step 4: Go to cart
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertTrue(cartPage.isCartPageLoaded(), "Cart page should be loaded");
        Assert.assertEquals(cartPage.getCartItemCount(), 3, "Cart should contain 3 items");

        // Step 5: Proceed to checkout
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();

        // Step 6: Fill checkout information
        CheckoutSummaryPage summaryPage = checkoutPage.fillAndContinue("Jane", "Smith", "67890");

        // Step 7: Verify order summary
        Assert.assertTrue(summaryPage.isCheckoutSummaryLoaded(), "Checkout summary should be loaded");
        Assert.assertEquals(summaryPage.getOrderItemCount(), 3, "Order summary should have 3 items");

        // Step 8: Complete purchase
        CheckoutCompletePage completePage = summaryPage.clickFinish();

        // Step 9: Verify order completion
        Assert.assertTrue(completePage.isOrderCompleted(), "Order should be completed");
        Assert.assertEquals(completePage.getThankYouMessage(), "Thank you for your order!",
                "Thank you message should be displayed");
    }
}