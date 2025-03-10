package com.parabank.tests;

import com.parabank.pageobjects.AccountOverviewPage;
import com.parabank.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AccountSummaryTest extends BaseTest {

    @Test (dataProvider = "getData")
    public void accountSummary_TC104(HashMap<String,String> input) throws InterruptedException {

        landingPage.loginApplication(input.get("username"),input.get("password"));
        Assert.assertTrue(landingPage.getLoginStatus().contains("Welcome"));

        AccountOverviewPage acctOverview = landingPage.goToAcctOverviewPage();
        acctOverview.accountOverview();
        Assert.assertEquals(acctOverview.getAcctDetailsText(),"Account Details");

        acctOverview.goToLogOut();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> mapData=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//com//parabank//data//data.json");
        return new Object[][] {{mapData.get(0)}};
    }
}
