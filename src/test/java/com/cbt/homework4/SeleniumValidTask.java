package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.*;

public class SeleniumValidTask {

    private WebDriver driver;
    private String selenium = "https://www.selenium.dev/documentation/en/";
private By tag_a_By = By.tagName("a");


    @Test
    public void verifyValidLinks(){
        List<WebElement> allLinks = driver.findElements(tag_a_By);
        for (int i=0; i<allLinks.size();i++){
            String href = allLinks.get(i).getAttribute("href");//get URL
            try {
                URL url = new URL(href);
                HttpURLConnection  httpURLConnection = (HttpURLConnection)url.openConnection();//Get connection
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.connect();//connect
                assertTrue(httpURLConnection.getResponseCode()==200);//get response
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(selenium);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
