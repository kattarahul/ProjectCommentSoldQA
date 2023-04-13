package seleniumwaits;

import constants.ExplicitWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumWaits {


    WebDriver driver;
    WebDriverWait wait;
    ExplicitWait explicitWait;

    public SeleniumWaits(WebDriver waitdriver)
    {
        this.driver =waitdriver;
        explicitWait = new ExplicitWait();
    }

    public WebElement waitToClickElement(WebElement element)
    {
        wait = new WebDriverWait(driver,Duration.ofSeconds(explicitWait.seconds));
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(element));
        return element1;
    }

}
