package org.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resuablemethods.SeleniumActions;

public class CollectionsPage {


    WebDriver driver;

    SeleniumActions seleniumActions ;
    JavascriptExecutor jse;
    Actions act;


    public CollectionsPage(WebDriver colloectionsdriver)
    {
        this.driver =colloectionsdriver;
        PageFactory.initElements(colloectionsdriver,this);
        seleniumActions = new SeleniumActions(driver);
        jse = (JavascriptExecutor)driver;
        act = new Actions(driver);
    }



    @FindBy(xpath = "//img[contains(@alt,'Product: Velvet Duvet Cover')]")
    WebElement hoverImageElement;

    @FindBy(xpath = "//button[text()='Quickview']")
    WebElement quickViewElement;


    @FindBy(xpath = "//label[text()='King']")
    WebElement sizeOfProductElement;

    @FindBy(css = "button[type='submit']")
    WebElement addToCartButtonElement;


    @FindBy(linkText = "Checkout")
    WebElement checkoutButtonElement;

    public void products()
    {
        seleniumActions.waitToClickElement(hoverImageElement);
        act.moveToElement(hoverImageElement).build().perform();
        jse.executeScript("arguments[0].click();",quickViewElement);
    }


    public void addToCart() throws InterruptedException {
        Thread.sleep(3000);
        jse.executeScript("arguments[0].click();",sizeOfProductElement);
        jse.executeScript("arguments[0].click();",addToCartButtonElement);

        seleniumActions.waitToClickElement(checkoutButtonElement);
        act.moveToElement(checkoutButtonElement).click().build().perform();

    }


}
