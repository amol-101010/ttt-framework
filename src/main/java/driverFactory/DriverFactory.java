package driverFactory;

import base.BaseTest;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public WebDriver createInstance(String browser){
        WebDriver driver = BrowserFactory.valueOf(BaseTest.config.browser().toUpperCase()).createLocalDriver();
        return driver;
    }
}
