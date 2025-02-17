package practice.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practice.pageobjects.CartPage;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;
    WebDriverWait wait;

    public AbstractComponent(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @FindBy(css="button[routerlink*='cart']")
    WebElement cart;

    public void waitForElementToAppear(By findBy)
    {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement element)
    {
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    public void waitForElementToInvisible(WebElement ele)
    {
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public CartPage goToCartPage()
    {
        cart.click();
        CartPage cartPage=new CartPage(driver);
        return cartPage;
    }
}
