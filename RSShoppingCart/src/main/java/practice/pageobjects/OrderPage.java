package practice.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="tr td:nth-child(3)")
    List<WebElement> orderedProducts;

    public Boolean orderProductCheck(String prod)
    {
        return orderedProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(prod));
    }
}
