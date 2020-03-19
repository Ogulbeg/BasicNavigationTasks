package com.cbt.homework3;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.UrlFilter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FileUploadedTest {

    private RemoteWebDriver driver;
    private String URL = "https://practice-cybertekschool.herokuapp.com";
    private By fileUploadedLinkBy = By.linkText("File Upload");
    private By chooseFileBy = By.id("file-upload");
    private By uploadedFileBy = By.id("file-submit");
    private By successMessageBy = By.tagName("h3");

    private By autoCompleteBy = By.linkText("Autocomplete");
    private By myCountryBy = By.id("myCountry");
    private By submitBntBy = By.xpath("//input[@type='button']");
    private By expectedMessageBy = By.id("result");
    private By dropDownBy=By.id("myCountryautocomplete-list");

    @Test
    public void testCase7() {
        driver.findElement(fileUploadedLinkBy).click();
        driver.findElement(chooseFileBy).sendKeys("/Users/olyaa/Desktop/What Is Agile Methodology?.txt");
        BrowserUtil.wait(5);
        driver.findElement(uploadedFileBy).click();
        BrowserUtil.wait(4);
        String actual = driver.findElement(successMessageBy).getText();
        String expected = "File Uploaded!";
        assertEquals(actual, expected);

    }

    @Test
    public void testCase8() {
        driver.findElement(autoCompleteBy).click();
        BrowserUtil.wait(3);
        driver.findElement(myCountryBy).sendKeys("United States");
        BrowserUtil.wait(3);
        driver.findElement(dropDownBy).click();
        BrowserUtil.wait(3);
        driver.findElement(submitBntBy).click();

        String autoCompleteExpected = "You selected: United States of America";
        BrowserUtil.wait(3);
        String autoCompleteActual = driver.findElement(expectedMessageBy).getText();
        assertEquals(autoCompleteActual, autoCompleteExpected);
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        BrowserUtil.wait(5);

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
