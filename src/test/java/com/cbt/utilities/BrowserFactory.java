package com.cbt.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

    public static WebDriver getDriver(String driverName) {

        String OS = System.getProperty("os.name").toLowerCase();
        //String driverPath = "<path to Microsoft edge driver>";
        if ((OS.contains("Win") && driverName.equalsIgnoreCase("safari")) ||
                (OS.contains("mac") && driverName.equalsIgnoreCase("edge "))) {
            return null;

        }
        if (driverName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().version("79.0").setup();
            return new ChromeDriver();
        } else if (driverName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }else if(driverName.equalsIgnoreCase("edge")){
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        } else {
            return new SafariDriver();
        }

    }
}