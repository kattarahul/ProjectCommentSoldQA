package org.pages;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import resuablemethods.SeleniumActions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class CheckoutPage {

    WebDriver driver ;
    SeleniumActions seleniumActions ;
    JavascriptExecutor jse;
    Actions act;

    public CheckoutPage (WebDriver checkoutpagedriver)
    {
        this.driver = checkoutpagedriver;
        PageFactory.initElements(checkoutpagedriver, this);
        seleniumActions = new SeleniumActions(driver);
        jse = (JavascriptExecutor) driver;
        act = new Actions(driver);
    }



    @FindBy(xpath = "//input[@value='delivery']")
    WebElement deliveryRadioButton;


    @FindBy(xpath = "//span[text()=' Street Address ']/following-sibling::input[@autocomplete='address-line1']")
    WebElement addressLineOneElement;

    @FindBy(xpath = "//span[text()=' Street Address Line 2 ']/following-sibling::input")
    WebElement addressLineTwoElemeent;

    @FindBy(xpath = "//span[text()='City']/following-sibling::input")
    WebElement cityElement;

    @FindBy(xpath = "//select[@name='state']")
    WebElement stateElement;

    @FindBy(xpath = "//span[text()='Zip Code']/following-sibling::input")
    WebElement zipcode;

    @FindBy(xpath = "//button[text()=' Save Address ']")
    WebElement saveButton;

    @FindBy(xpath = "//input[@name='currentPaymentType']")
    WebElement creditCardRadioButton;

    @FindBy(xpath = "//input[@placeholder= 'Card number']")
    WebElement creditCardNumberElement;

    @FindBy(xpath = "//input[@placeholder='MM / YY']")
    WebElement expireDateElement;
    @FindBy(xpath = "//input[@name='cvc']")
    WebElement cvcNumberElement;

    @FindBy(xpath = "//button[text()='Add Card']")
    WebElement addCardElement;


    @FindBy(xpath = "//iframe[@role='presentation'")
    WebElement frameSwitching;

    @FindBy(xpath = "//span[text()=' Logout ']")
    WebElement loginOut;

    @FindBy(css = "div[class='close']")
    WebElement closeIcon;

    @FindBy(xpath = "//button[text()=' Pay With Card ']")
    WebElement payWithCardButton;


    @FindBy(xpath = "//div[@class='inner']/h1")
    WebElement orderConfirmationText;
    @FindBy(xpath = "//button[text()='Add Code ']")
    WebElement addCouponCode;

    @FindBy(css = "input[placeholder='Enter Code']")
    WebElement enterCouponCodeText;

    @FindBy(css = "#save-button")
    WebElement applyButton;



    public void shippingAddress() throws IOException, ParseException {

            try {
                seleniumActions.waitToClickElement(deliveryRadioButton);
                seleniumActions.elementToClick(deliveryRadioButton);

                JSONParser jsonParser = new JSONParser();
                FileReader fileReader = new FileReader("src/main/java/testdata/ShippingAddress.json");
                Object obj = jsonParser.parse(fileReader);
                JSONObject jsonObject = (JSONObject) obj;


                String addressLineOne = jsonObject.get("StreetAddressLineOne").toString();
                String addressLineTwo = jsonObject.get("StreetAddressLineTwo").toString();
                String city = jsonObject.get("City").toString();
                String zipCode = jsonObject.get("zipCode").toString();

                seleniumActions.enterTextField(addressLineOneElement, addressLineOne);
                seleniumActions.enterTextField(addressLineTwoElemeent, addressLineTwo);
                seleniumActions.enterTextField(cityElement, city);
                seleniumActions.enterTextField(zipcode, zipCode);

                Select select = new Select(stateElement);
                select.selectByVisibleText("Texas");
                seleniumActions.elementToClick(saveButton);
            } catch (NoSuchElementException nse) {
                System.out.println(nse);
            }
    }



    public void paymentMethod() throws IOException, ParseException, InterruptedException {

        seleniumActions.waitToClickElement(creditCardRadioButton);
        act.moveToElement(creditCardRadioButton).click().build().perform();

 /*
       SWITCHING TO FRAME CODE MUST WRITTEN HERE................... T

*/
       /* JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader("src/main/java/testdata/CreditCardDetails.json");
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;


        String creditCardNumber = jsonObject.get("CreditCardCNumber").toString();
        String expireDate = jsonObject.get("ExpireDate").toString();
        String  cvvOrCVC= jsonObject.get("CVVorCVC").toString();
        try {
            seleniumActions.waitToClickElement(creditCardNumberElement);
            seleniumActions.enterTextField(creditCardNumberElement, creditCardNumber);
            seleniumActions.enterTextField(expireDateElement, expireDate);
            seleniumActions.enterTextField(cvcNumberElement, cvvOrCVC);
        }
        catch(NoSuchElementException nse) {
            Thread.sleep(3000);
            jse.executeScript("arguments[0].click();",addCardElement);

        }*/
//
//            Thread.sleep(3000);
//            act.moveToElement(closeIcon).click().build().perform();

    }
    public void payWithCard()
    {
        seleniumActions.waitToClickElement(payWithCardButton);
        jse.executeScript("arguments[0].click();",payWithCardButton);

        orderCompleteMessage();

    }

    public  void orderCompleteMessage()
    {
        seleniumActions.waitToClickElement(orderConfirmationText);
        String exceptedMessage = orderConfirmationText.getText();
        String actualMessage ="Your Order Is Complete!";
        Assert.assertEquals(actualMessage,exceptedMessage);
    }

    public void logout()
    {
        driver.navigate().to("https://qatest.commentsoldrt.com/account");
        seleniumActions.waitToClickElement(loginOut);
        act.moveToElement(loginOut).click().build().perform();
    }
    public void addingCoupon()
    {
        seleniumActions.waitToClickElement(addCouponCode);
        act.moveToElement(addCouponCode).click().build().perform();
        seleniumActions.waitToClickElement(enterCouponCodeText);
        seleniumActions.enterTextField(enterCouponCodeText,"5OFF20");
        act.moveToElement(applyButton).click().build().perform();
    }



}
