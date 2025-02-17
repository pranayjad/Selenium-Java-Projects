package practice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import practice.abstractComponents.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {

    WebDriver driver;
    public ProductCatalogue(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".col-lg-4")
    List<WebElement> products;

    @FindBy(css=".ng-animating")
    WebElement ngAnimating;


    By productsBy = By.cssSelector(".col-lg-4");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");


    //By.cssSelector(".col-lg-4"))
    public List<WebElement> getProductList()
    {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement product= getProductList().stream()
                .filter(s->s.findElement(By.cssSelector("b")).getText().contains(productName))
                .findFirst().orElse(null);
        return product;
    }

    public void addProductToCart(String productName)
    {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToInvisible(ngAnimating);
    }
}
