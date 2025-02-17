package practice.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import practice.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String prod="ZARA COAT 3";

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");

        LandingPage ld=new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("ppj@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("zaq1@WSX");
        driver.findElement(By.id("login")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("button[routerlink*='myorders']")).getText().contains("  ORDERS"));

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

        //Add to cart - Serach product with name 'Zara' and then add it to cart

        List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));
        System.out.println("Total products - "+products.size());

        List<String> prodNames=products.stream().map(s->s.findElement(By.cssSelector("div div h5 b")).getText()).collect(Collectors.toList());
        System.out.println(prodNames.get(0));

        WebElement product=products.stream()
                .filter(s->s.findElement(By.cssSelector("b")).getText().contains(prod))
                .findFirst().orElse(null);

        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        //check for msg added successfully
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        //click on cart
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

        List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
        Assert.assertTrue(cartProducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(prod)));

        //click checkout
        driver.findElement(By.cssSelector(".totalRow .btn")).click();

       // driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");

        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"Ind").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));

        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click(); // xpath = (//button[contains(@class,'ta-item')])[2]

        //a.sendKeys(Keys.ARROW_DOWN).build().perform();
        //a.sendKeys(Keys.ARROW_DOWN, Keys.ENTER).build().perform();

        //Place order
        driver.findElement(By.cssSelector(".action__submit")).click();
        //System.out.println(driver.findElement(By.tagName("h1")).getText());
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().equalsIgnoreCase("Thankyou for the order."));


        Thread.sleep(2000L);

        driver.quit();

    }
}
