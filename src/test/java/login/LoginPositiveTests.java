package login;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPositiveTests extends BaseTest {

    @Test(description = "TC_LOGIN_001: Valid Login with Standard User")
    public void TC_LOGIN_001_ValidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.doLogin("standard_user","secret_sauce");
        Assert.assertTrue(  driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(),"User Logged in successfully.");
    }


    @Test(description = "TC_LOGIN_001: Valid Login with Performance_glitch_user")
    public void TC_LOGIN_002_ValidLoginWith_Performance_glitch_user() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.doLogin("performance_glitch_user","secret_sauce");
//        Assert.assertTrue(  driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(),"User Logged in successfully.");
    }
}
