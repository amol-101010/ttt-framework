package login;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginPositiveTests {

    @Test(description = "TC_LOGIN_001: Valid Login with Standard User")
    public void TC_LOGIN_001_ValidLogin() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("standard_user","secret_sauce");
        Assert.assertTrue(  driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(),"User Logged in successfully.");
    }


    @Test(description = "TC_LOGIN_001: Valid Login with Performance_glitch_user")
    public void TC_LOGIN_002_ValidLoginWith_Performance_glitch_user() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("performance_glitch_user","secret_sauce");
//        Assert.assertTrue(  driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(),"User Logged in successfully.");
    }
}
