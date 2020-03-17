package com.cbt.homework3;

import com.cbt.utilities.BrowserUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class Registration_Form {
    private WebDriver driver;
    private String URL = "https://practice-cybertekschool.herokuapp.com/";
    private By formBy = By.linkText("Registration Form");
    private By dobBy = By.name("birthday");
    private By warningMessageBy = By.xpath("//small[text()='The date of birth is not valid']");
    private By checkBoxesBy = By.className("form-check-label");
    private By firstNameBy = By.name("firstname");
    private By warningMessageBy2 = By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']");
    private By lastNameBy = By.name("lastname");
    private By warningMessageBy3 = By.xpath("//small[text()='The last name must be more than 2 and less than 64 characters long']");
    private By userNameBy = By.name("username");
    private By emailBy = By.name("email");
    private By passwordBy = By.name("password");
    private By phoneNumberBy = By.name("phone");
    private By genderMaleBy = By.xpath("//input[@value='male']");
    private By genderFemaleBy = By.xpath("//input[@value='female']");
    private By genderOthersBy = By.xpath("//input[@value='other']");
    private By departmentBy = By.name("department");
    private By jobTitleBy = By.name("job_title");
    private By cPlusBy = By.xpath("//label[@for='inlineCheckbox1']");
    private By javaBy = By.xpath("//label[@for='inlineCheckbox2']");
    private By javaScript = By.xpath("//label[@for='inlineCheckbox3']");
    private By signUpBy = By.id("wooden_spoon");
    private By successMessageBy = By.xpath("//p[text()]");


    @Test(description = " Verify that warning message is displayed:“The date of birth is not valid”")
    public void testCase1() {

        BrowserUtil.wait(2);
        driver.findElement(dobBy).sendKeys("wrong_dob");

        String actual = driver.findElement(warningMessageBy).getText();
        String expected = "The date of birth is not valid";
        assertEquals(actual, expected);
    }

    @Test(description = " Verify that following options for programming languages are displayed: c++, java,JavaScript")
    public void testCase2() {
        List<WebElement> actual = driver.findElements(checkBoxesBy);
        String[] languages = {"c++", "java", "JavaScript"};

        Boolean expected = false;

        for (int i = 0; i < languages.length; i++) {
            if (languages[i].equals(actual.get(i).getText())) {
                expected = true;
            }
        }
        assertTrue(expected);
    }

    @Test(description = "Verify that warning message is displayed: first name must be more than 2 and less than 64 characters long")
    public void testCase3() {
        driver.findElement(firstNameBy).sendKeys("a");
        String actual = driver.findElement(warningMessageBy2).getText();
        String expected = "first name must be more than 2 and less than 64 characters long";

        assertEquals(actual, expected);
    }

    @Test(description = "Verify that warning message is displayed: The last name must be more than 2 and less than 64 characters long”")
    public void testCase4() {
        BrowserUtil.wait(3);
        driver.findElement(lastNameBy).sendKeys("b");
        String actual = driver.findElement(warningMessageBy3).getText();
        String expected = "The last name must be more than 2 and less than 64 characters long";
        assertEquals(actual, expected);
    }

    @Test(description = "Verify that following success message is displayed: “You've successfully completed registration!")
    public void testCase5() {
        BrowserUtil.wait(3);
        driver.findElement(firstNameBy).sendKeys("tom");
        driver.findElement(lastNameBy).sendKeys("smith");
        driver.findElement(userNameBy).sendKeys("tomsmith");
        driver.findElement(emailBy).sendKeys("tomsmith@gmail.com");
        driver.findElement(passwordBy).sendKeys("UserUser123");
        driver.findElement(phoneNumberBy).sendKeys("571-000-0000");
        driver.findElement(genderFemaleBy).click();
        driver.findElement(dobBy).sendKeys("01/01/1995");
        driver.findElement(departmentBy).click();
        Select dropDown = new Select(driver.findElement(departmentBy));
        dropDown.selectByVisibleText("Department of Agriculture");
        driver.findElement(jobTitleBy).click();
        Select dropDown1 = new Select(driver.findElement(jobTitleBy));
        dropDown1.selectByVisibleText("SDET");
        driver.findElement(javaBy).click();
        driver.findElement(signUpBy).click();
        BrowserUtil.wait(3);

        String actual = driver.findElement(successMessageBy).getText();
        String excepted = "You've successfully completed registration!";
        assertEquals(actual, excepted);


    }


    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        BrowserUtil.wait(2);
        driver.get(URL);
        driver.findElement(formBy).click();

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
