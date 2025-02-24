package practice.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import practice.TestComponents.BaseTest;
import practice.TestComponents.Retry;

public class ErrorValidation extends BaseTest {
    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void loginErrorValidation() throws InterruptedException {

        landingPage.loginApplication("pp@gmail.com","zaq1@WSX");
        Assert.assertEquals("Incorrect email aor password.",landingPage.getErrorMsg());

        Thread.sleep(2000L);
    }
}
