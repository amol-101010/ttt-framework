package base;

import entities.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class BaseTest {

    public static Map<Long, WebDriver> threadDriverMap = new ConcurrentHashMap<Long, WebDriver>();
    public static TestConfig config;

    @BeforeSuite
    public void suiteSetup() throws IOException {
        Properties props = new Properties();
        props.load(new FileReader("src/test/resources/config/test.properties"));
        config = ConfigFactory.create(TestConfig.class,props);
    }

    @Parameters({"env","url","browser","implicitWait"})
    @BeforeMethod
    public void SetupBrowser(@Optional String env, @Optional String url,  @Optional String browser, @Optional String implicitWait){
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
        WebDriver driver = new ChromeDriver(options);
        threadDriverMap.put(Thread.currentThread().getId(), driver);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Integer.parseInt(implicitWait)));
    }

    @AfterMethod
    public void tearDown(){
        getDriver().close();;
        getDriver().quit();
    }

    public WebDriver getDriver(){
        return threadDriverMap.get(Thread.currentThread().getId());
    }


}
