package com.parabank.pageobjects;

import com.parabank.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TransferFundPage extends AbstractComponent {

    WebDriver driver;
    public TransferFundPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="input[id='amount']")
    WebElement amount;

    @FindBy(css="input[value='Transfer']")
    WebElement transferButton;

    @FindBy(id="fromAccountId")
    WebElement fromAccount;

    @FindBy(id="toAccountId")
    WebElement toAccount;

    @FindBy(css="div[id='showResult'] h1")
    WebElement trxfStatus;

    By trxFundText = By.cssSelector("div[id='showForm'] h1.title");

    public void transferFund() {
        goToTransferFundPage();
        waitForElementToAppear(trxFundText);
        amount.sendKeys("1");
        selectAccount(fromAccount,0);
        selectAccount(toAccount,1);
        transferButton.click();
    }

    public String selectAccount(WebElement acctWebElement, int acctIndex)
    {
        Select acct = new Select(acctWebElement);
        acct.selectByIndex(acctIndex);
        return acct.getFirstSelectedOption().getText();
    }

    public String transferStatus()
    {
        waitForWebElementToAppear(trxfStatus);
        return trxfStatus.getText();
    }
}
