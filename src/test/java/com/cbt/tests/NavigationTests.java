package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.StringUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationTests {

    static WebDriver driver;

    public static void main(String[] args) throws Exception {
        chromeTest();
       // fireFoxTest();
        safariTest();
    }
/**
 * 1. Open browser
 * 2.Go to website https://google.com
 * 3. Save the title in a string variable
 * 4. Go to https://etsy.com
 * 5. Save the title in a string variable
 * 6. Navigate back to previous page
 * 7. Verify that title is same is in step 3
 * 8. Navigate forward to previous page
 * 9. Verify that title is same is in step 5
 */

    public static void chromeTest() throws Exception {
        driver = BrowserFactory.getDriver("chrome");

        driver.get("https://google.com");
        String title = driver.getTitle();

        driver.navigate().to("https://etsy.com");
        String title1= driver.getTitle();

        driver.navigate().back();
        StringUtility.verifyEquals(driver.getTitle(),title);
        Thread.sleep(4000);
        driver.navigate().forward();
        StringUtility.verifyEquals(driver.getTitle(),title1);


        Thread.sleep(4000);
        driver.quit();
    }

    public static void fireFoxTest() throws Exception {
        driver = BrowserFactory.getDriver("firefox");
        driver.get("https://google.com");
        String title = driver.getTitle();

        driver.navigate().to("https://etsy.com");
        String title1= driver.getTitle();

        driver.navigate().back();
        StringUtility.verifyEquals(driver.getTitle(),title);

        driver.navigate().forward();
        StringUtility.verifyEquals(driver.getTitle(),title1);


        Thread.sleep(4000);
        driver.quit();
    }

    public static void safariTest() throws Exception {
        driver = BrowserFactory.getDriver("safari");


        driver.get("https://google.com");
        String title = driver.getTitle();

        driver.navigate().to("https://etsy.com");
        String title1= driver.getTitle();

        driver.navigate().back();
        StringUtility.verifyEquals(driver.getTitle(),title);
        Thread.sleep(4000);
        driver.navigate().forward();
        StringUtility.verifyEquals(driver.getTitle(),title1);

        Thread.sleep(4000);
        driver.quit();
    }
}
