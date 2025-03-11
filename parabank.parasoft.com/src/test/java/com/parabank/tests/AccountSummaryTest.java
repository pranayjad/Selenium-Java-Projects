package com.parabank.tests;

import com.parabank.pageobjects.AccountOverviewPage;
import com.parabank.pageobjects.TransferFundPage;
import com.parabank.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class AccountSummaryTest extends BaseTest {

    public AccountOverviewPage acctOverview;

    @Test (dataProvider = "getData", priority = 0)
    public void accountSummary_TC104(HashMap<String,String> input) throws InterruptedException {

        landingPage.loginApplication(input.get("username"),input.get("password"));
        Assert.assertTrue(landingPage.getLoginStatus().contains("Welcome"));

        acctOverview = landingPage.goToAcctOverviewPage();
        acctOverview.accountOverview();
        Assert.assertEquals(acctOverview.getAcctDetailsText(),"Account Details");

        //acctOverview.goToLogOut();
    }

    @Test(groups={"Transfer"}, priority = 1, description = "TC_105 Transfer fund")
    public void transferFund_TC105()
    {
        TransferFundPage transferFundPage = acctOverview.goToTransferFundPage();
        transferFundPage.transferFund();
        Assert.assertTrue(transferFundPage.transferStatus().contains("Transfer Complete!"));

        transferFundPage.goToLogOut();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String,String>> mapData=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//com//parabank//data//data.json");
        return new Object[][] {{mapData.get(0)}};
    }
}
