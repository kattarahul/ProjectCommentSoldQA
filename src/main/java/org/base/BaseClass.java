package org.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;

public class BaseClass {

    public WebDriver driver;

    @BeforeTest
    public void openBrowser()
    {

        driver = new EdgeDriver();
        driver.get("https://qatest.commentsoldrt.com/webstore-register?destination=/account");
        driver.manage().window().maximize();

    }

}
