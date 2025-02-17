package practice.TestComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import practice.pageobjects.LandingPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//practice//resources//GlobalData.properties");
        prop.load(fis);
        String browserName=prop.getProperty("browser");

        switch(browserName)
        {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeTest
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        landingPage=new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
