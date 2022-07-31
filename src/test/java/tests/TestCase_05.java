package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import utilities.ConfigReader;

import static tests.TestCase_01.homePageNavigateAndVisible;

public class TestCase_05 {

    static HomePage homePage = new HomePage();
    static SignupPage signupPage = new SignupPage();
    static LoginPage loginPage = new LoginPage();

    @Test
    public void registerExistingEmail(){

        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        //3. Verify that home page is visible successfully
        homePageNavigateAndVisible();

        // 4. Click on 'Signup / Login' button
        // 5. Verify 'New User Signup!' is visible
        signupPage.signuploginButton.click();
        signupPage.newUserSignText.isDisplayed();

        //6. Enter name and already registered email address
        signupPage.nameBox.sendKeys(ConfigReader.getProperty("userName"));
        // homePage.emailBox.sendKeys(email); // registered email address above

        //7. Click 'Signup' button
        signupPage.signupButton.click();
        //8. Verify error 'Email Address already exist!' is visible
        loginPage.emailExistMsj.isDisplayed();

    }


}
