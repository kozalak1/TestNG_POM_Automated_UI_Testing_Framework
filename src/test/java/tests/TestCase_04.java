package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.ConfigReader;

import static tests.TestCase_01_02_05.email;
import static tests.TestCase_01_02_05.password;
import static utilities.JsUtils.clickElementByJS;

public class TestCase_04 {

    HomePage homePage = new HomePage();


    @Test
    public void logoutUser(){

        TestCase_01_02_05.registerUser();

        //4. Click on 'Signup / Login' button
        //5. Verify 'Login to your account' is visible
        clickElementByJS(homePage.signuploginButton);
        String actualLoginYourAccount = homePage.loginYourAccount.getText();
        //System.out.println(loginYourAccnt);
        Assert.assertEquals(actualLoginYourAccount, "Login to your account");

        //6. Enter correct email address and password
        //7. Click 'login' button

        homePage.loginEmail.sendKeys(email);
        homePage.loginPassword.sendKeys(password);
        homePage.loginSubmitButton.click();

        //8. Verify that 'Logged in as username' is visible
        String actualUsername= homePage.loggedUsername.getText();
        System.out.println(actualUsername);
        Assert.assertTrue(actualUsername.contains(ConfigReader.getProperty("userName")));

        //9. Click 'Logout' button
        homePage.logoutButton.click();

        //10. Verify that user is navigated to login page

        homePage.signuploginButton.isDisplayed();

    }
}
