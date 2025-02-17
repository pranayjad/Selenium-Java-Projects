package practice.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import practice.TestComponents.BaseTest;

import java.io.IOException;

public class ErrorValidation extends BaseTest {
    @Test
    public void submitOrder() throws InterruptedException {

        landingPage.loginApplication("pp@gmail.com","zaq1@WSX");
        Assert.assertEquals(landingPage.getErrorMsg(), "Incorrect email or password.");

        Thread.sleep(2000L);
    }
}
