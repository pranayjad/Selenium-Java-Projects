package com.parabank.pageobjects;

import com.parabank.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OpenNewAccountPage extends AbstractComponent {

    WebDriver driver;
    public OpenNewAccountPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="select[id='type']")
    WebElement acctType;

    @FindBy(css="input[value*='Open New']")
    WebElement openAcctButton;

    @FindBy(css="div[id*='Result'] h1")
    WebElement acctOpenStatus;

    @FindBy(css="a[id='newAccountId']")
    WebElement newAcctNo;

    By openNewAcctsBy = By.cssSelector("div[id='openAccountForm'] h1");

    public void openNewAcct()
    {
        goToOpenNewAcctPage();
        waitForElementToAppear(openNewAcctsBy);
        selectAcctType();
        openAcctButton.click();
    }

    public void selectAcctType()
    {
        Select selectAcctType=new Select(acctType);
        selectAcctType.selectByVisibleText("SAVINGS");
    }

    public String getAcctOpenStatus()
    {
        waitForWebElementToAppear(acctOpenStatus);
        return acctOpenStatus.getText();
    }

    public String getNewAcctNo()
    {
        return newAcctNo.getText();
    }

}
