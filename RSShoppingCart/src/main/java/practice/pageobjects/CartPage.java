package practice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".cartSection h3")
    List<WebElement> cartProducts;

    @FindBy(css=".totalRow .btn")
    WebElement checkoutButton;

    public CheckoutPage checkout()
    {
        checkoutButton.click();
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        return checkoutPage;
    }

    public Boolean cartProductCheck(String prod)
    {
        Boolean match=cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(prod));
        return match;
    }
}
