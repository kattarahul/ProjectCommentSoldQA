package org.pages;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resuablemethods.SeleniumActions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SignIn {


    WebDriver driver;
    SeleniumActions seleniumActions;

    public SignIn(WebDriver signindriver) {

        this.driver = signindriver;
        PageFactory.initElements(signindriver, this);
        seleniumActions = new SeleniumActions(driver);

    }


    @FindBy(css = "input[type='email']")
    WebElement emailElement;

    @FindBy(css = "input[type='password']")
    WebElement passwordElement;

    @FindBy(xpath = "//button[text()='Log In']")
    WebElement loginButton;


    public void signInWithEmailAndPassword() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader("src/main/java/testdata/UserDetails.json");
        Object obj = jsonParser.parse(fileReader);
        JSONObject jsonObject = (JSONObject) obj;

        String email = jsonObject.get("Email").toString();
        String password = jsonObject.get("Password").toString();


        seleniumActions.enterTextField(emailElement, email);
        seleniumActions.enterTextField(passwordElement, password);
        seleniumActions.elementToClick(loginButton);


    }


    public void navigateToUrl()
    {
        driver.navigate().to("https://qatest.commentsoldrt.com/webstore-login?destination=/account");
    }
}
