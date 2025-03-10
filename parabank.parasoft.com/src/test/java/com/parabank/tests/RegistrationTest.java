package com.parabank.tests;

import com.parabank.pageobjects.RegistrationPage;
import com.parabank.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void registration()  {

        RegistrationPage registrationPage = landingPage.goToRegisterPage();
        registrationPage.registerUser("TestUser1");

        Assert.assertTrue(registrationPage.getRegistrationStatus().contains("created successfully"));

        registrationPage.goToLogOut();
    }
}
