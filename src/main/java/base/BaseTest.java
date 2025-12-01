package base;

import entities.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class BaseTest {
    public static Map<Long, WebDriver> threadDriverMap = new ConcurrentHashMap<Long, WebDriver>();
    public static TestConfig config;

    @BeforeSuite
    public void setupSuite() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(("src/test/resources/config/test.properties")));
        config = ConfigFactory.create(TestConfig.class,properties);
    }


    @Parameters({"env","url","browser","implicitWait"})
    @BeforeMethod
    public void SetupBrowser(@Optional String env, @Optional String url,  @Optional String browser, @Optional String implicitWait){
        WebDriver driver = new ChromeDriver();
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
