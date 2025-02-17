package practice.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import practice.TestComponents.BaseTest;
import practice.pageobjects.*;

import java.io.IOException;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    @Test
    public void submitOrder() throws InterruptedException {

        String prod="ZARA COAT 3";
        String country="india";

        ProductCatalogue productCatalogue= landingPage.loginApplication("ppj@gmail.com","zaq1@WSX");

        List<WebElement> products=productCatalogue.getProductList();
        productCatalogue.addProductToCart(prod);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match= cartPage.cartProductCheck(prod);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.checkout();

        checkoutPage.selectCountry(country);
        ConfirmationPage confirmationPage = checkoutPage.placeOrder();

        String confirmMsg = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("Thankyou for the order."));

        Thread.sleep(2000L);
    }
}
