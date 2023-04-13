package org.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resuablemethods.SeleniumActions;

public class StorePage {


    WebDriver driver ;

    SeleniumActions seleniumActions;
    JavascriptExecutor jse;


    public StorePage(WebDriver storedriver)
    {
        this.driver = storedriver;
        PageFactory.initElements(storedriver,this);
        seleniumActions =new SeleniumActions(driver);
        jse = (JavascriptExecutor) driver;

    }

    @FindBy(css = "svg[aria-label='close']")
    WebElement closePopUpElement;



    @FindBy(xpath = "//div[@class='container']/div/div")
    WebElement scrollUpto;


    @FindBy(xpath = "//div[@class='container']/div/div/a")
    WebElement whatsNewElement;


    public void popUp()
    {
        seleniumActions.waitToClickElement(closePopUpElement);
        seleniumActions.moveToElement(closePopUpElement);
    }


    public void categoryImage()
    {
        jse.executeScript("arguments[0].scrollIntoView(true);",scrollUpto);
        jse.executeScript("arguments[0].click();",whatsNewElement);

    }

}
