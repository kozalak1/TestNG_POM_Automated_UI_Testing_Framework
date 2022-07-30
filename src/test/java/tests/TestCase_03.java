package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.ConfigReader;

import static utilities.JsUtils.clickElementByJS;

public class TestCase_03 {

    HomePage homePage = new HomePage();

    @Test
    public void negativeLoginTest () {

        TestCase_01_02_05.homePageNavigateAndVisible();

        //4. Click on 'Signup / Login' button
        //5. Verify 'Login to your account' is visible
        clickElementByJS(homePage.signuploginButton);

        String actualLoginYourAccount = homePage.loginYourAccount.getText();
        //System.out.println(loginYourAccnt);
        Assert.assertEquals(actualLoginYourAccount, "Login to your account");

        //6. Enter incorrect email address and password
        //7. Click 'login' button
        homePage.loginEmail.sendKeys(ConfigReader.getProperty("incorrectEmail"));
        homePage.loginPassword.sendKeys(ConfigReader.getProperty("incorrectPassword"));
        homePage.loginSubmitButton.click();

        //8. Verify error 'Your email or password is incorrect!' is visible
        homePage.emailAndPasswordIncorrectMsj.isDisplayed();


    }

}
