package practice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import practice.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    //WebElement userEmail=driver.findElement(By.id("userEmail"));

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement loginButton;

    @FindBy(css="[class*=flyInOut]")
    WebElement loginErrorMsg;

    public ProductCatalogue loginApplication(String email, String password)
    {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        loginButton.click();
        ProductCatalogue productCatalogue=new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMsg()
    {
        waitForWebElementToAppear(loginErrorMsg);
        return loginErrorMsg.getText();
    }
}
