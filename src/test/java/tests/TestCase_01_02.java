package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utilities.ConfigReader;
import utilities.Driver;

import static utilities.Driver.driver;
import static utilities.JsUtils.clickElementByJS;
import static utilities.JsUtils.scrollIntoVIewJS;


public class TestCase_01_02 {


    HomePage homePage = new HomePage();
    Faker faker = new Faker();

    static String email ="";
    static String password = "";


    @Test
    public void registerUser () {
       // 1. Launch browser
       // 2. Navigate to url 'http://automationexercise.com'

       Driver.getDriver().get(ConfigReader.getProperty("aeUrl"));

       //  3. Verify that home page is visible successfully

        String actualHomePageTitle = Driver.getDriver().getTitle();
        System.out.println(actualHomePageTitle);
        String expectedHomePageTitle = ConfigReader.getProperty("title");
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);

        // 4. Click on 'Signup / Login' button
        // 5. Verify 'New User Signup!' is visible
        homePage.signuploginButton.click();
        homePage.newUserSignText.isDisplayed();

        //6. Enter name and email address
        //7. Click 'Signup' button
        //8. Verify that 'ENTER ACCOUNT INFORMATION' is visible

        homePage.nameBox.sendKeys(ConfigReader.getProperty("name"));

        email= faker.internet().emailAddress();
        System.out.println(email);
        homePage.emailBox.sendKeys(email);

        homePage.signupButton.submit();
        String enterAccountInfoText = homePage.enterAccountInfo.getText();
        String expectedText = "ENTER ACCOUNT INFORMATION";
        Assert.assertEquals(enterAccountInfoText, expectedText);

        //9. Fill details: Title, Name, Email, Password, Date of birth
        //10. Select checkbox 'Sign up for our newsletter!'
        //11. Select checkbox 'Receive special offers from our partners!'
        //12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        //13. Click 'Create Account button'
        //14. Verify that 'ACCOUNT CREATED!' is visible

        homePage.accountTitleRadioButton.click();
        //homePage.accountName.sendKeys(ConfigReader.getProperty("name"));
        //homePage.accountEmail.sendKeys(ConfigReader.getProperty("email"));

        password=faker.internet().password();
        System.out.println(password);
        homePage.accountPassword.sendKeys(password);

        Select select1 = new Select(homePage.dateOfBirth_days);
        select1.selectByValue("15");

        Select select2 = new Select(homePage.dateOfBirth_months);
        select2.selectByIndex(1);

        Select select3 = new Select(homePage.dateOfBirth_years);
        select3.selectByValue("1983");

        homePage.checkboxNewsletter.click();
        homePage.checkboxOptin.click();
        //homePage.addressFirstName.sendKeys(faker.name().firstName());

        Actions actions = new Actions(driver);

        actions.click(homePage.addressFirstName)
                .sendKeys(faker.name().firstName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.name().lastName())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.expression("testx"))
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().fullAddress())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ARROW_DOWN)
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().state())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().city())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.address().zipCode())
                .sendKeys(Keys.TAB)
                .sendKeys(faker.phoneNumber().cellPhone())
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.ENTER)
                .perform(); // Actions class'ında bu komut mutlaka en sonunda yer almak zorunda

        // 15. Click 'Continue' button
        //16. Verify that 'Logged in as username' is visible

        scrollIntoVIewJS(homePage.continueButton);
        homePage.continueButton.click();
      homePage.loggedInText.isDisplayed();

        //17. Click 'Delete Account' button
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button

        clickElementByJS(homePage.deleteAccountButton);

        Driver.getDriver().navigate().back();
        Driver.getDriver().navigate().refresh();

        homePage.logoutButton.click();


    }

    @Test (dependsOnMethods = "registerUser" )
    public void loginPositiveTest () {

//1. Launch browser
//2. Navigate to url 'http://automationexercise.com'
        Driver.getDriver().get(ConfigReader.getProperty("aeUrl"));

//3. Verify that home page is visible successfully
        String title = Driver.getDriver().getTitle();
        System.out.println(title);
        Assert.assertTrue(title.contains("Automation"), "Home page is visible");

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
//9. Click 'Delete Account' button
//10. Verify that 'ACCOUNT DELETED!' is visible
        String actualUsername= homePage.loggedUsername.getText();
        System.out.println(actualUsername);
        Assert.assertTrue(actualUsername.contains(ConfigReader.getProperty("name")));
        homePage.deleteAccountButton.click();



    }

// login olmak için gerekli olan email ve passwordu registerUser isimli testde faker ile ürettiğimiz için 2 testi birlikte - ya da peş peşe çalıştırmamız gerekiyor.

}
