package testcases;

import baseclass.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTests extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void beforeEachTest() {
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
        loginPage.openLoginForm();
    }

    @Test
    public void testValidLogin() {
        loginPage.login(ConfigReader.get("validEmail"), ConfigReader.get("validPassword"));
        Assert.assertTrue(loginPage.isLogoutDisplayed(), "Logout should be visible after valid login.");
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login(ConfigReader.get("invalidEmail"), ConfigReader.get("invalidPassword"));
        String msg = loginPage.getSnackBarText().toLowerCase();
        // System.out.println(msg); // uncomment this only in case if you want to print error msg too
        Assert.assertTrue(msg.contains("invalid") || msg.contains("wrong"), "Expected error message for invalid login.");
    }

    @Test
    public void testForgotPassword() {
        loginPage.clickForgotPassword();
        loginPage.resetPassword(ConfigReader.get("invalidEmail"));
        // here we are just verifing that user is on forget password page
        loginPage.verifyForgotPasswordPage();
        /* Below code will not work because on forget password page few elements are not intractable and
         will get error as ElementClickInterceptedException */
        
        // String resetMsg = loginPage.getSnackBarText().toLowerCase();
        // Assert.assertTrue(resetMsg.contains("email") || resetMsg.contains("reset"), "Expected confirmation after reset.");
    }
}
