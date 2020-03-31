package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class School_Com_Task {
    private WebDriver driver;
    private String URL = "https://www.w3schools.com/";
    private By tagName_a_By = By.tagName("a");

    @Test
    public void getTextFromTagName_a() {
        List<WebElement> allLinks = driver.findElements(tagName_a_By);

        for (WebElement eachLink : allLinks) {
            if (eachLink.isDisplayed()) {
                System.out.println( eachLink.getText());
                if (!eachLink.getAttribute("href").contains("void")) {
                    System.out.println( eachLink.getAttribute("href"));
                }

            }
        }
    }

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
