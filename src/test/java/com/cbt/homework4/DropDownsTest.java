package com.cbt.homework4;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.BrowserUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class DropDownsTest {
    private WebDriver driver;
    private String URL = "http://practice.cybertekschool.com/dropdown";
    public static int year;


    @DataProvider(name = "testData")
    public static Object[][] testData() {
        Calendar calendar = Calendar.getInstance();
        String currentYear = String.valueOf(calendar.get(Calendar.YEAR));
        String currentMonth = String.valueOf(Month.of(calendar.get(Calendar.MONTH) + 1));
        String currentDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        return new Object[][]{{"1", currentYear}, {"2", currentMonth}, {"3", currentDay}};
    }

    @Test(dataProvider = "testData")
    public void dataOfBirthday(String index, String expected) {

        Select select = new Select(driver.findElement(By.xpath("//h6[text()='Select your date of birth']/following-sibling::select[" + index + "]")));

        String actual = select.getFirstSelectedOption().getText().toUpperCase();
        Assert.assertEquals(actual, expected);
    }

    private static int findExpectedNumberOfDays(int index) {
        int expected = 0;
        boolean isLeap = (year % 4 == 0);
        if (index % 2 == 0) {
            if (index >= 0 && index <= 6) {
                expected = 31;
            } else if (index >= 7 && index <= 11) {
                expected = 30;
            }
        } else if (index % 2 == 1) {
            if (index == 1 && isLeap) {
                expected = 29;
            } else if (index == 1 && !isLeap) {
                expected = 28;
            } else if (index >= 0 && index <= 6 && index != 1) {
                expected = 30;
            } else if (index >= 7 && index <= 11 && index != 1) {
                expected = 31;
            }
        }
        return expected;
    }

    @Test
    public void yearsMonthsDay() {
        Random rYear = new Random();
        year = rYear.nextInt(100) + 1921; //getting random year
        System.out.println(year);
        Select selectY = new Select(driver.findElement(By.id("year")));
        selectY.selectByValue(String.valueOf(year));   //selecting random year
        BrowserUtil.wait(2);
        Select selectM = new Select(driver.findElement(By.id("month")));
        for (int i = 0; i < 12; i++) {
            selectM.selectByValue(String.valueOf(i));
            List<WebElement> days = driver.findElements(By.xpath("//select[@id='day']/option"));
            int expected = findExpectedNumberOfDays(i);
            Assert.assertEquals(days.size(), expected);
        }

    }

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
