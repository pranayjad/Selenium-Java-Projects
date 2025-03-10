package com.parabank.pageobjects;

import com.parabank.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(css="input[id='amount']")
    WebElement fromAcct;

    @FindBy(css="input[id='amount']")
    WebElement toAccount;

    By trxFundText = By.cssSelector("div[id='showForm'] h1.title");

    public void transferFund()
    {
        goToTransferFundPage();
        waitForElementToAppear(trxFundText);

    }
}
