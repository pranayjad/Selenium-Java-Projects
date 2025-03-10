package com.parabank.pageobjects;

import com.parabank.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends AbstractComponent {

    WebDriver driver;
    public RegistrationPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="customer.firstName")
    WebElement firstNameText;
    @FindBy(id="customer.lastName")
    WebElement lastNameText;
    @FindBy(id = "customer.address.street")
    WebElement addressText;
    @FindBy(id="customer.address.city")
    WebElement cityText;
    @FindBy(id="customer.address.state")
    WebElement stateText;
    @FindBy(id="customer.address.zipCode")
    WebElement zipCodeText;
    @FindBy(id="customer.phoneNumber")
    WebElement phNumberText;
    @FindBy(id="customer.ssn")
    WebElement ssnText;
    @FindBy(id="customer.username")
    WebElement usernameText;
    @FindBy(id="customer.password")
    WebElement passwordText;
    @FindBy(id="repeatedPassword")
    WebElement confirmText;
    @FindBy(css="input[value='Register']")
    WebElement registerButton;

    By custFNameLabel = By.id("customer.firstName");

    @FindBy(css="div[id='rightPanel'] p")
    WebElement registrationStatus;

    @FindBy(css="span.error")
    WebElement registrationStatus_ExistingUserData;

    public void registerUser(String username)
    {
        goToRegisterPage();
        waitForElementToAppear(custFNameLabel);
        registerUserDataEntry(username);
        registerButton.click();
    }

    public void registerUserDataEntry(String username)
    {
        firstNameText.sendKeys(username);
        lastNameText.sendKeys("Lname");
        addressText.sendKeys("Add1");
        cityText.sendKeys("City1");
        stateText.sendKeys("State1");
        zipCodeText.sendKeys("12345");
        phNumberText.sendKeys("123456789");
        ssnText.sendKeys("123564");
        usernameText.sendKeys(username);
        passwordText.sendKeys("test");
        confirmText.sendKeys("test");
    }

    public String getRegistrationStatus()
    {
        waitForWebElementToAppear(registrationStatus);
        return registrationStatus.getText();
    }

    public String getRegistrationStatus_ExistingUserData()
    {
        waitForWebElementToAppear(registrationStatus_ExistingUserData);
        return registrationStatus_ExistingUserData.getText();
    }
}
