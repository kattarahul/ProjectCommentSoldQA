package resuablemethods;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import seleniumwaits.SeleniumWaits;

import java.nio.channels.SeekableByteChannel;

public class SeleniumActions {

    WebDriver driver;
    SeleniumWaits seleniumWaits;

    public SeleniumActions(WebDriver actionsdriver)
    {

        this.driver = actionsdriver;
        seleniumWaits =new SeleniumWaits(actionsdriver);

    }




    public void elementToClick(WebElement element)
    {

        element.click();
    }


    public void waitToClickElement(WebElement element)
    {
        seleniumWaits.waitToClickElement(element);
    }

    public boolean enterTextField(WebElement element, String key)
    {
        boolean flag = false;
        try {
            element.sendKeys(key);
            flag = true;
        }
        catch(NoSuchElementException nse)
        {
            System.out.println(nse);
            flag=false;
        }
        return flag;
    }


    public void moveToElement(WebElement element )
    {
        try {
            Actions act = new Actions(driver);
            act.moveToElement(element).click().build().perform();
        }
        catch (NoSuchElementException nse)
        {
            System.out.println(nse);
        }

    }


    public  void javaScriptClick(WebElement element)
    {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("argument[0].click();",element);
    }

//    public WebElement selectTheElement(WebElement element,String option)
//    {
//        Select select = new Select(element);
//
//        switch ()
//        {
//
//            case "text":
//                select.selectByVisibleText();
//                break;
//            case "int" :
//                select.selectByIndex();
//                break;
//            case "value":
//                select.selectByValue();
//                break;
//            default:
//                System.out.println("Provide Proper Input ");
//                break;
//
//
//        }
//
//    }



}
