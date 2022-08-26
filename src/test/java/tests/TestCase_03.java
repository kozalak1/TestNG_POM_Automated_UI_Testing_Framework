package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import utilities.ConfigReader;
import utilities.ReusableMethods;

import static utilities.JsUtils.clickElementByJS;

public class TestCase_03 {

    static HomePage homePage = new HomePage();
    static SignupPage signupPage = new SignupPage();
    static LoginPage loginPage = new LoginPage();

    @Test
    public void negativeLoginTest () {

       ReusableMethods.homePageNavigateAndVisible();

        //4. Click on 'Signup / Login' button
        //5. Verify 'Login to your account' is visible
        clickElementByJS(signupPage.signuploginButton);

        String actualLoginYourAccount = loginPage.loginYourAccount.getText();
        //System.out.println(loginYourAccnt);
        Assert.assertEquals(actualLoginYourAccount, "Login to your account");

        //6. Enter incorrect email address and password
        //7. Click 'login' button
        loginPage.loginEmail.sendKeys(ConfigReader.getProperty("incorrectEmail"));
        loginPage.loginPassword.sendKeys(ConfigReader.getProperty("incorrectPassword"));
        loginPage.loginSubmitButton.click();

        //8. Verify error 'Your email or password is incorrect!' is visible
        homePage.emailAndPasswordIncorrectMsj.isDisplayed();


    }

}
