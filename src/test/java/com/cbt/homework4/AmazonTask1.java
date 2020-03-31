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

import static org.testng.Assert.*;

public class AmazonTask1 {
    private WebDriver driver;
    private String URL = "https://www.amazon.com";
    private By allBy = By.cssSelector("span[class='nav-search-label']");
    private By departmentBy = By.xpath("//option[@value='search-alias=aps']/following-sibling::option");
    private String URLDirectory = "https://www.amazon.com/gp/site-directory";
    private By titlesBy = By.className("fsdDeptTitle");

    @Test
    public void allBtnVerify() {
        String actual = driver.findElement(allBy).getText();
        System.out.println("actual " + actual);
        List<WebElement> departments = driver.findElements(departmentBy);

        boolean sorted = true;
        String previous = "Audible Books & Originals";
        for (WebElement each : departments) {
            String current = each.getText().trim();

            System.out.println("each " + current);
            System.out.println("previous = " + previous);
            System.out.println("current.compareTo(previous) = " + current.compareTo(previous));

            if (current.compareTo(previous) < 0) {
                sorted = false;
                break;
            }
            previous = current;
        }
        assertEquals(actual, "All");
        assertFalse(sorted);
    }

    @Test
    public void containsDirectory() {
        driver.get(URLDirectory);
        List<WebElement> mainTitles = driver.findElements(titlesBy);
        List<WebElement> allDepartments = driver.findElements(departmentBy);
        int count = 0;
        for (int i = 0; i < mainTitles.size(); i++) {
            for (int j = 0; j < allDepartments.size(); j++) {
                if (mainTitles.get(i).getText().contains(allDepartments.get(j).getText())) {
                    count++;
                }
            }
        }

        System.out.println(count);

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
