package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationTests {

    static WebDriver driver;

    public static void main(String[] args) throws Exception {
        chromeTest();
        fireFoxTest();
        safariTest();
    }

    /**
     * WebDriver driver = DriverFactory.createDriver("chrome");
     *         driver.get("http://ebay.com");
     *         Thread.sleep(2000);//to wait 2 seconds
     *         driver.findElement(By.id("gh-ac")).sendKeys("java book");
     *         Thread.sleep(2000);//to wait 2 seconds
     *         driver.findElement(By.id("gh-btn")).click();
     *         Thread.sleep(4000);//to wait 2 seconds
     *         WebElement searchResults = driver.findElement(By.tagName("h1"));
     *         String[] searchSentence = searchResults.getText().split(" ");
     *         System.out.println(Arrays.toString(searchSentence));
     *         System.out.println(searchSentence[0]);
     *         driver.quit();
     * @throws Exception
     */
    public static void chromeTest() throws Exception {
        driver = BrowserFactory.getDriver("chrome");

        driver.get("https://google.com");

        Thread.sleep(2000);





        Thread.sleep(4000);
        driver.quit();
    }

    public static void fireFoxTest() throws Exception {
        driver = BrowserFactory.getDriver("firefox");

        Thread.sleep(4000);
        driver.quit();
    }

    public static void safariTest() throws Exception {
        driver = BrowserFactory.getDriver("safari");

        Thread.sleep(4000);
        driver.quit();
    }
}
