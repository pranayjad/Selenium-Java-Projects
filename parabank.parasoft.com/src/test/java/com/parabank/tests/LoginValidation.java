package com.parabank.tests;

import com.parabank.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class LoginValidation extends BaseTest {

    @Test (groups = {"HappyPath"}, dataProvider = "getData", priority = 0, description = "TC-99, Login happy path test case")
    public void login_TC99(HashMap<String,String> input) {

        landingPage.loginApplication(input.get("username"),input.get("password"));
        Assert.assertTrue(landingPage.getLoginStatus().contains("Welcome"));

        landingPage.goToLogOut();
    }

    @Test (groups = {"NegativeScenario"}, priority = 1,description = "TC-100, Login with invalid Username and valid Password")
    public void login_InvalidUsername_TC100()
    {
        String username="Test10";
        String password ="test";

        landingPage.loginApplication(username,password);
        Assert.assertTrue(landingPage.getLoginErrorMsg().contains("could not be verified"));
    }

    @Test (dataProvider = "getData", groups = {"NegativeScenario"}, priority = 2, description = "TC-101, Login with valid Username and invalid Password")
    public void login_InvalidPassword_TC101(HashMap<String,String> input)
    {
        landingPage.loginApplication(input.get("username"),"1234");
        Assert.assertTrue(landingPage.getLoginErrorMsg().contains("could not be verified"));
    }

    @DataProvider
    public Object [][] getData() throws IOException {
        List<HashMap<String,String>> mapData = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//com//parabank//data//data.json");
        return new Object [][] {{mapData.get(0)}};
    }
}
