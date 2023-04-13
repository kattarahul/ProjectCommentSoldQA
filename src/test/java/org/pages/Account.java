package org.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resuablemethods.SeleniumActions;

public class Account {

    WebDriver driver;
    SeleniumActions seleniumActions;
    JavascriptExecutor jse;

    public Account(WebDriver accountdriver)
    {
        this.driver = accountdriver;
        PageFactory.initElements(accountdriver,this);
        seleniumActions = new SeleniumActions(accountdriver);
        jse = (JavascriptExecutor) driver;

    }



    @FindBy(xpath = "//a[text()=' Shop ']")
    WebElement shopLinkTextElement;





    public void shopModule() throws InterruptedException
    {
        seleniumActions.waitToClickElement(shopLinkTextElement);
        jse.executeScript("arguments[0].click();",shopLinkTextElement);
    }




}
