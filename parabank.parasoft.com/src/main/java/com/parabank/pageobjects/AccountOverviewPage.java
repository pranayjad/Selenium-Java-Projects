package com.parabank.pageobjects;

import com.parabank.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountOverviewPage extends AbstractComponent {

    WebDriver driver;
    public AccountOverviewPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="a[href*='activity.htm?']")
    WebElement acctLink;

    @FindBy(css="div[id='accountDetails'] h1")
    WebElement acctDetailsText;

    By avblAmtBy = By.cssSelector("table[id='accountTable'] tr th:nth-child(3)");

    public void accountOverview() throws InterruptedException {
        goToAcctOverviewPage();
        waitForElementToAppear(avblAmtBy);
        Thread.sleep(1000L);
        acctLink.click();
    }

    public String getAcctDetailsText()
    {
        waitForWebElementToAppear(acctDetailsText);
        return acctDetailsText.getText();
    }

}
