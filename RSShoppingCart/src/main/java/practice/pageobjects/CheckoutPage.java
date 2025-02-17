package practice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import practice.abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="input[placeholder='Select Country']")
    WebElement countryDropdown;

    @FindBy(css=".ta-item:nth-of-type(2)")
    WebElement selectCountry;

    @FindBy(css=".action__submit")
    WebElement placeOrderButton;

    By countryResults= By.cssSelector(".ta-item");

    public void selectCountry(String countryName)
    {
        Actions a = new Actions(driver);
        a.sendKeys(countryDropdown,countryName).build().perform();
        waitForElementToAppear(countryResults);
        selectCountry.click();
    }

    public ConfirmationPage placeOrder()
    {
        placeOrderButton.click();
        return new ConfirmationPage(driver);
    }
}
