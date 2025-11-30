package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    By txtUsername = By.id("user-name");
    By txtPassword = By.id("password");
    By btnLogin = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void doLogin( String username, String password){
        this.driver.findElement(txtUsername).click();
        this.driver.findElement(txtUsername).sendKeys(username);
        this.driver.findElement(txtPassword).click();
        this.driver.findElement(txtPassword).sendKeys(password);
        this.driver.findElement(btnLogin).click();
    }


}
