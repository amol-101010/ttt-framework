package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.util.HashMap;
import java.util.Map;

public enum BrowserFactory {
    CHROME{
        @Override
        public WebDriver createLocalDriver(){
            return new ChromeDriver(getOptions());
        }
        @Override
        public ChromeOptions getOptions(){
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            // Turn off password manager / “save password” UI
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            // Turn off “Change your password – data breach” popup
            // (Chrome leak-detection feature)
            prefs.put("profile.password_manager_leak_detection", false);
            // some Chrome versions use this key:
            prefs.put("password_leak_detection_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            // optional: remove “Chrome is being controlled by automated test software” banner
            options.setExperimentalOption("excludeSwitches",
                    new String[]{"enable-automation"});
            return options;
        }
    },
    EDGE{
        @Override
        public WebDriver createLocalDriver(){
            return new EdgeDriver(getOptions());
        }
        @Override
        public EdgeOptions getOptions(){
            EdgeOptions options = new EdgeOptions();
            Map<String, Object> prefs = new HashMap<>();
            // Turn off password manager / “save password” UI
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            // Turn off “Change your password – data breach” popup
            // (Chrome leak-detection feature)
            prefs.put("profile.password_manager_leak_detection", false);
            // some Chrome versions use this key:
            prefs.put("password_leak_detection_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            // optional: remove “Chrome is being controlled by automated test software” banner
            options.setExperimentalOption("excludeSwitches",
                    new String[]{"enable-automation"});
            return options;
        }

    },
    FIREFOX{
        @Override
        public WebDriver createLocalDriver(){
            return new FirefoxDriver(getOptions());
        }
        @Override
        public FirefoxOptions getOptions(){
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");
            return options;
        }

    };

    public abstract WebDriver createLocalDriver();
    public abstract AbstractDriverOptions<?> getOptions();
}
