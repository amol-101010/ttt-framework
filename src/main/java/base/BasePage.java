package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public boolean clickElement(By by){
        WebElement element = driver.findElement(by);
        if(isWebElementEnabled(element)){
            element.click();
            return true;
        }else {
            System.out.println("Element is not displayed or enabled ");
            return false;
        }
    }

    public boolean clearTextAndType(By by, String inputString) {
        WebElement element = driver.findElement(by);
        if(isWebElementEnabled(element) ){
            element.clear();
            element.sendKeys(inputString);
            return true;
        }else{
            System.out.println("Element is not displayed or enabled ");
            return false;
        }
        
    }

    private boolean isWebElementDisplayed(WebElement element) {
        try{
            if (element.isDisplayed())
                return true;
        }catch ( Exception e){

        }
        return false;
    }

    private boolean isWebElementEnabled(WebElement element) {
        try{
            if (isWebElementDisplayed(element)){
                if(!element.getAttribute("class").contains("disabled")){
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println("Element is not enabled");
        }
        return false;
    }
}
