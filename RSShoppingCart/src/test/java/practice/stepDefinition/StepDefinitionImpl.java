package practice.stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import practice.TestComponents.BaseTest;
import practice.pageobjects.*;

import java.io.IOException;

public class StepDefinitionImpl extends BaseTest {

    LandingPage landingPage;
    ProductCatalogue productCatalogue;
    CartPage cartPage;
    CheckoutPage checkoutPage;;
    ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce page")
    public void i_landed_on_Ecommerce_page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password)
    {
        productCatalogue= landingPage.loginApplication(username,password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName)
    {
        productCatalogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_order(String productName) throws InterruptedException {
        cartPage = productCatalogue.goToCartPage();
        Boolean match= cartPage.cartProductCheck(productName);
        Assert.assertTrue(match);
        checkoutPage = cartPage.checkout();
        checkoutPage.selectCountry("India");
        confirmationPage = checkoutPage.placeOrder();
    }

    @Then("{string} message is displayed on Confirmation page")
    public void message_is_displayed_on_confirmation_page(String msg)
    {
        String confirmMsg = confirmationPage.getConfirmationMsg();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase(msg));
        driver.quit();
    }

    @Then("{string} message is displayed")
    public void message_is_displayed(String msg)
    {
        Assert.assertEquals(msg,landingPage.getErrorMsg());
        driver.quit();
    }
}
