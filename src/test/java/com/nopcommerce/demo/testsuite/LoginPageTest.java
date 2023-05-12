package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.LoginPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    String email;
    HomePage homePage;
    LoginPage loginPage;

    RegisterPageTest registerPageTest;

    @BeforeMethod
    public void inIt() {
        homePage = new HomePage();
        loginPage = new LoginPage();
        registerPageTest = new RegisterPageTest();

    }

    @Test
    public void userShouldNavigateToLoginPageSuccessFully() {
        String exceptedMessage = "Welcome, Please Sign In!";
        homePage.clickOnLoginLink();
        String actualMessage = loginPage.getWelcomeText();
        Assert.assertEquals(exceptedMessage, actualMessage);
    }

    @Test
    public void verifyTheErrorMessageWithInValidCredentials() {
        String exceptedMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found";
        homePage.clickOnLoginLink();
        loginPage.enterEmailId("DPPatel@gmail.com");
        loginPage.enterPassword("AimHigh123");
        loginPage.clickOnLoginButton();
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(exceptedMessage, actualMessage);
    }

    @Test
    public void VerifyThatUserShouldLogOutSuccessFully() throws InterruptedException {
        registerPageTest.inIt();
        registerPageTest.VerifyThatUserShouldCreateAccountSuccessfully();
        homePage.clickOnLoginLink();
        loginPage.enterEmailId(registerPageTest.email);
        loginPage.enterPassword("AimHigh123");
        loginPage.clickOnLoginButton();
        homePage.clickOnLogOutLink();
        Assert.assertTrue(homePage.loginLinkIsDisplayed());
    }

}
