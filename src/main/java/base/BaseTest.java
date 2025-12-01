package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaseTest {
    public static Map<Long, WebDriver> threadDriverMap = new ConcurrentHashMap<Long, WebDriver>();

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
