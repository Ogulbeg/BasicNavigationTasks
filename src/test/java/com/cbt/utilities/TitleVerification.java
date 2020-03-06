package com.cbt.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TitleVerification {

    static WebDriver driver;
    public static void main(String[] args) throws Exception {
        List<String> urls = Arrays.asList("http://practice.cybertekschool.com/",
                "http://practice.cybertekschool.com/dropdown",
                "http://practice.cybertekschool.com/login");
//        WebDriverManager.chromedriver().version("79").setup();
//        WebDriver driver = new ChromeDriver();

        driver=BrowserFactory.getDriver("chrome");

        Set<String> unique = new HashSet<>();
        for (String each:urls) {
            driver.get(each);
            unique.add(driver.getTitle());
        }
        if (unique.size()==1){
            System.out.println("They are equal!");
        } else {
            System.out.println("They are NOT equal!");
        }
        Thread.sleep(3000);
    /* 1st approach
    String title1 = driver.getTitle();
     boolean sw = true;
        for (String each:urls) {
            driver.get(each);
            if (!driver.getTitle().equals(title1)){
                sw = false;
            }
        }
        if (sw==false){
            System.out.println("They are NOT equal!");
        } else {
            System.out.println("They are equal!");
        }*/


        driver.quit();
    }
}
