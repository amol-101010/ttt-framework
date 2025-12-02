package utils;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;

public class DriverUtil {
    public static String takeScreenshot(){
        WebDriver driver = BaseTest.threadDriverMap.get(Thread.currentThread().getId());
        if (driver == null){
            return "";
        }
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String screenshot = "screenshot_" + System.currentTimeMillis() + ".png";
        String screenshotPath = "target/extentReport/"+screenshot;

        File destinationFile = new File(screenshotPath);
        try{
            destinationFile.getParentFile().mkdirs();
            FileUtils.copyFile(sourceFile,destinationFile);
        }catch (IOException e) {
            return "";
        }
        return screenshot;

    }
}
