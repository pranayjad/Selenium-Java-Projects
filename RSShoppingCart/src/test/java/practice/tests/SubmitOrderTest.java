package practice.tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import practice.TestComponents.BaseTest;
import practice.pageobjects.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String prod="ZARA COAT 3";
    @Test(dataProvider = "getData",groups = {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws InterruptedException {

        String country="india";

        ProductCatalogue productCatalogue= landingPage.loginApplication(input.get("email"),input.get("password"));

        List<WebElement> products=productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("prod"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match= cartPage.cartProductCheck(input.get("prod"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.checkout();

        checkoutPage.selectCountry(country);
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();

        String confirmMsg = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

        Thread.sleep(2000L);
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void verifyOrder()
    {
        ProductCatalogue productCatalogue= landingPage.loginApplication("ppj@gmail.com","zaq1@WSX");
        OrderPage orderPage=productCatalogue.goToOrderPage();
        Assert.assertTrue(orderPage.orderProductCheck(prod));
    }

    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String,String> map=new HashMap<String,String>();
//        map.put("email","ppj@gmail.com");
//        map.put("password","zaq1@WSX");
//        map.put("prod","ZARA COAT 3");
//
//        HashMap<String,String> map1=new HashMap<String,String>();
//        map1.put("email","anshika@gmail.com");
//        map1.put("password","Iamking@000");
//        map1.put("prod","ZARA COAT 3");

        //return new Object [][] {{"ppj@gmail.com","zaq1@WSX","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}};
        Path jsonFilePath= Paths.get("src/test/java/practice/data/PurchaseOrder.json");
        List<HashMap<String,String>> mapData = getJsonDataToMap(jsonFilePath.toAbsolutePath().toString());

        //List<HashMap<String,String>> mapData = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//practice//data//PurchaseOrder.json");
        return new Object [][] {{mapData.get(0)},{mapData.get(1)}};
    }

    public void getScreenshot(String testCaseName) throws IOException {
       TakesScreenshot ts = (TakesScreenshot)driver;
       File sourceFile = ts.getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(sourceFile,new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png"));
    }
}
