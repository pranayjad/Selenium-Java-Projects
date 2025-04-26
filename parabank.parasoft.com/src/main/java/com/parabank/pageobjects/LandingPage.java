package com.parabank.pageobjects;

import com.parabank.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="input[name='username']")
    WebElement username;

    @FindBy(css="input[name='password']")
    WebElement password;

    @FindBy(css="input.button")
    WebElement logIn;

    @FindBy(css="p.smallText")
    WebElement loginStatus;

    By acctViewBy=By.xpath("//h1[contains(text(),'Accounts Overview')]");

    //@FindBy(css="div[id='rightPanel'] p")
    @FindBy(css="p.error")
    WebElement loginErrorMsg;

    public void loginApplication(String user, String pwd)
    {
        username.sendKeys(user);
        password.sendKeys(pwd);
        logIn.click();
    }

    public String getLoginStatus()
    {
        waitForElementToAppear(acctViewBy);
        return loginStatus.getText();
    }

    public String getLoginErrorMsg()
    {
        waitForWebElementToAppear(loginErrorMsg);
        return loginErrorMsg.getText();
    }
}
