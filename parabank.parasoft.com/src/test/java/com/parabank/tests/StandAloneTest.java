package com.parabank.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        String username="TestUser1";
        String password ="test123";

        driver.manage().window().maximize();

        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        //Registration
        registration(driver,wait,username,password);

        //Account summary
        accountSummary(driver,username,password);

        driver.quit();

    }

    public static void registration(WebDriver driver, WebDriverWait wait, String username, String password) throws InterruptedException {
        //Registration
        driver.findElement(By.cssSelector("a[href*='register.htm']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("customer.firstName")));

        //List<WebElement> formFields=driver.findElements(By.cssSelector("table[class='form2'] tr input[class='input']"));

        driver.findElement(By.id("customer.firstName")).sendKeys("Fname");
        driver.findElement(By.id("customer.lastName")).sendKeys("Lname");
        driver.findElement(By.id("customer.address.street")).sendKeys("Add1");
        driver.findElement(By.id("customer.address.city")).sendKeys("City1");
        driver.findElement(By.id("customer.address.state")).sendKeys("State1");
        driver.findElement(By.id("customer.address.zipCode")).sendKeys("12345");
        driver.findElement(By.id("customer.phoneNumber")).sendKeys("123456789");
        driver.findElement(By.id("customer.ssn")).sendKeys("123564");
        driver.findElement(By.id("customer.username")).sendKeys(username);
        driver.findElement(By.id("customer.password")).sendKeys(password);
        driver.findElement(By.id("repeatedPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("input[value='Register']")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("div[id='rightPanel'] p")).getText().contains("created successfully"));

        Thread.sleep(1000L);
        driver.findElement(By.linkText("Log Out")).click(); // a[href*='logout']
    }

    public static void accountSummary(WebDriver driver, String username, String password) throws InterruptedException {
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);

        Thread.sleep(1000L);
        driver.findElement(By.cssSelector("input.button")).click();

        //Account summary
        driver.findElement(By.linkText("Accounts Overview")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("table[id='accountTable'] tr th:nth-child(3)")).getText(),"Available Amount");

        Thread.sleep(1000L);
        //driver.findElement(By.cssSelector("table[id='accountTable'] tbody tr td:nth-child(1) a")).click();
        driver.findElement(By.cssSelector("a[href*='activity.htm?']")).click();
        //String acctNo=driver.findElement(By.cssSelector("table[id='accountTable'] tbody tr td:nth-child(1) a")).getText();

        Assert.assertEquals(driver.findElement(By.cssSelector("div[id='accountDetails'] h1")).getText(), "Account Details");

        Thread.sleep(1000L);
        driver.findElement(By.linkText("Log Out")).click(); // a[href*='logout']
    }
}
