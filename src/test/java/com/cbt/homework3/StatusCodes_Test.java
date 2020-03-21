package com.cbt.homework3;

import com.cbt.utilities.BrowserUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StatusCodes_Test {
private WebDriver driver;
private String URL="https://practice-cybertekschool.herokuapp.com";
private By statusCodeBy= By.linkText("Status Codes");


    @DataProvider(name ="testData")
    public static Object [] testData(){
        return new Object [] {"200","301","404","500"};
    }


    @Test(dataProvider ="testData")
    public void testCase9(String code){
        driver.findElement(statusCodeBy).click();
        BrowserUtil.wait(3);
        driver.findElement(By.linkText(code)).click();
        BrowserUtil.wait(3);
        String actual9 = driver.findElement(By.xpath("//p")).getText();
        String expected9="This page returned a "+code +" status code";
        Assert.assertTrue(actual9.contains(expected9));

    }




@BeforeMethod
    public void setup(){
    WebDriverManager.chromedriver().version("79").setup();
    driver= new ChromeDriver();
    driver.get(URL);
    driver.manage().window().maximize();
    BrowserUtil.wait(4);
}
@AfterMethod
    public void testDown(){
    driver.quit();
}


}
