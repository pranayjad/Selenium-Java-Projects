package com.parabank.abstractComponents;

import com.parabank.pageobjects.AccountOverviewPage;
import com.parabank.pageobjects.OpenNewAccountPage;
import com.parabank.pageobjects.RegistrationPage;
import com.parabank.pageobjects.TransferFundPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    WebDriverWait wait;

    public AbstractComponent(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(css="a[href*='register.htm']")
    WebElement registrationLink;

    @FindBy(linkText="Accounts Overview")
    WebElement acctOverview;

    @FindBy(css="a[href*='openaccount']")
    WebElement openNewAcctLink;

    @FindBy(css="a[href*='transfer']")
    WebElement transferFundLink;

    @FindBy(linkText="Log Out")
    WebElement logOut;

    public void waitForElementToAppear(By findBy)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement element)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void goToApplication()
    {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    public RegistrationPage goToRegisterPage()
    {
        registrationLink.click();
        return new RegistrationPage(driver);
    }

    public AccountOverviewPage goToAcctOverviewPage()
    {
        acctOverview.click();
        return new AccountOverviewPage(driver);
    }

    public OpenNewAccountPage goToOpenNewAcctPage()
    {
        openNewAcctLink.click();
        return new OpenNewAccountPage(driver);
    }

    public TransferFundPage goToTransferFundPage()
    {
        transferFundLink.click();
        return new TransferFundPage(driver);
    }

    public void goToLogOut()
    {
        logOut.click();
    }

}
