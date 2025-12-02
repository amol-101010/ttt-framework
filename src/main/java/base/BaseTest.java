package base;

import driverFactory.DriverFactory;
import entities.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
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
        WebDriver driver = new DriverFactory().createInstance(BaseTest.config.browser());
        threadDriverMap.put(Thread.currentThread().getId(), driver);
        driver.get(BaseTest.config.url());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(BaseTest.config.implicitWait()));

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
