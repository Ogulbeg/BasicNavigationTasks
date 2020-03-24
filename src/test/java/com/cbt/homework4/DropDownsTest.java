package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Month;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class DropDownsTest {
    private WebDriver driver;
    private String URL="http://practice.cybertekschool.com/dropdown";


@DataProvider(name="testData")
public static Object[][]testData(){
        Calendar calendar = Calendar.getInstance();
        String currentYear = String.valueOf(calendar.get(Calendar.YEAR));
        String currentMonth =String.valueOf(Month.of (calendar.get(Calendar.MONTH)+1));
        String currentDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return new Object[][]{{"1",currentYear},{"2",currentMonth},{"3",currentDay}};
    }





@Test(dataProvider = "testData")
public void dataOfBirthday(String index,String expected){

    Select select = new Select(driver.findElement(By.xpath("//h6[text()='Select your date of birth']/following-sibling::select["+index+"]")));

    String actual =select.getFirstSelectedOption().getText().toUpperCase();
    Assert.assertEquals(actual,expected);


}


    @BeforeMethod
    public void setup(){
        driver= BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown(){
       driver.quit();
    }
}
