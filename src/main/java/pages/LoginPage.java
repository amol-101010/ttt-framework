package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    WebDriver driver;

    By txtUsername = By.id("user-name");
    By txtPassword = By.id("password");
    By btnLogin = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void doLogin( String username, String password) {
        clearTextAndType(txtUsername, username);
        clearTextAndType(txtPassword,password);
        clickElement(btnLogin);
    }


}
