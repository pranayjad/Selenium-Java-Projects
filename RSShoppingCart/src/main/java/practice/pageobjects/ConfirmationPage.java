package practice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import practice.abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(tagName="h1")
    WebElement confimationMsg;

    public String getConfirmationMsg()
    {
        return confimationMsg.getText();
    }
}
