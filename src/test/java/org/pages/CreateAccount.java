package org.pages;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resuablemethods.SeleniumActions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CreateAccount {

    WebDriver driver;
    SeleniumActions seleniumActions;
    JavascriptExecutor jse;

    SignIn signIn;


    public CreateAccount(WebDriver createAccountdriver)
    {
        this.driver = createAccountdriver;
        seleniumActions = new SeleniumActions(createAccountdriver);
        PageFactory.initElements(createAccountdriver,this);
        jse = (JavascriptExecutor) driver;
        signIn = new SignIn(createAccountdriver);
    }

    @FindBy(xpath = "//input[contains(@name,'customer[first_name]')]")
    WebElement firstNameElement;

    @FindBy(xpath = "//input[contains(@name,'customer[last_name]')]")
    WebElement lastNameElement;


    @FindBy(css = "input[type='email']")
    WebElement emailElement;

    @FindBy(css = "input[type='password']")
    WebElement passwordElement;

    @FindBy(css = "button[type='submit']")
    WebElement createAccountElement;


    @FindBy(xpath = "//button[contains(text(),' Confirm ')]")
    WebElement confirmButton;

    @FindBy(css = "div[role='alert']")
    WebElement alertMessageElement;

    @FindBy(xpath = "//a[contains(text(),'Sign In')]")
    WebElement signInLinkTextButton;



    public void fillingDetails() throws IOException, InterruptedException, ParseException {

        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader("src/main/java/testdata/UserDetails.json");
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;

        String firstName = jsonObject.get("FirstName").toString();
        String lastName = jsonObject.get("LastName").toString();
        String email = jsonObject.get("Email").toString();
        String password = jsonObject.get("Password").toString();

        seleniumActions.enterTextField(firstNameElement, firstName);
        seleniumActions.enterTextField(lastNameElement, lastName);
        seleniumActions.enterTextField(emailElement, email);
        seleniumActions.enterTextField(passwordElement, password);
        seleniumActions.elementToClick(createAccountElement);

        try {
            alertPopUp();
            String alertActualMessage = "This email address is already associated with an account. If this account is yours, you can ";

            jse.executeScript("arguments[0].scrollIntoView(true);", createAccountElement);
            seleniumActions.moveToElement(signInLinkTextButton);

            signIn.signInWithEmailAndPassword();
        }

        catch (NoSuchElementException nse)
        {
            System.out.println("no element found :"+nse);
        }

    }

        public String alertPopUp()
        {
            String  alertExceptedMessage = alertMessageElement.getText();
            return alertExceptedMessage;
        }





    public void welcomePopup() throws InterruptedException {
        try {
            Thread.sleep(3000);
            jse.executeScript("arguments[0].click();", confirmButton);
        }
        catch (NoSuchElementException nse)
        {
            System.out.println(" no confirm button found :"+nse);
        }

    }

}
