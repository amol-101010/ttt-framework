package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginNegativeTests {

    @Test(description = "TC_LOGIN_001: Invalid Login with Wrong User name")
    public void TC_LOGIN_001_InvalidLogiNonExitanceUser() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("standard_user100","secret_sauce");
//        Assert.assertFalse( driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(),"User Logged in successfully.");
    }


    @Test(description = "TC_LOGIN_002: Invalid Login with Locked Out User")
    public void TC_LOGIN_002_InvalidLogiLockedOutUser() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("locked_out_user","secret_sauce");

//        Assert.assertFalse( driver.findElement(By.id("react-burger-menu-btn")).isDisplayed(),"User Logged in successfully.");//TODO: isDisplayed Cannot be used in Assert
    }

}
