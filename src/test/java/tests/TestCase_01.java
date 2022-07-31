package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.io.IOException;

import static utilities.Driver.driver;
import static utilities.ExcelUtil.writeToExcel;
import static utilities.JsUtils.clickElementByJS;
import static utilities.JsUtils.scrollIntoVIewJS;

public class TestCase_01 {

    static HomePage homePage = new HomePage();
    static SignupPage signupPage = new SignupPage();

    static Faker faker = new Faker();
    public static String email ="";
    static String password = "";

// sitenin ana sayfasına gitmek ve ana sayfanın görünür olması sürekli gerekli olacagı için ayrı bir metod haline getirildi

    public static void homePageNavigateAndVisible(){

        // 1. Launch browser
        // 2. Navigate to url 'http://automationexercise.com'

        Driver.getDriver().get(ConfigReader.getProperty("aeUrl"));

        //  3. Verify that home page is visible successfully

        String actualHomePageTitle = Driver.getDriver().getTitle();
        System.out.println(actualHomePageTitle);
        String expectedHomePageTitle = ConfigReader.getProperty("title");
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);
}
    @Test
    public static void registerUser () throws IOException {

        homePageNavigateAndVisible();

        // 4. Click on 'Signup / Login' button
        // 5. Verify 'New User Signup!' is visible
        signupPage.signuploginButton.click();
        signupPage.newUserSignText.isDisplayed();

        //6. Enter name and email address
        //7. Click 'Signup' button
        //8. Verify that 'ENTER ACCOUNT INFORMATION' is visible

        signupPage.nameBox.sendKeys(ConfigReader.getProperty("userName"));

        email= faker.internet().emailAddress();

        signupPage.emailBox.sendKeys(email);

        signupPage.signupButton.submit();
        String enterAccountInfoText = signupPage.enterAccountInfo.getText();
        String expectedText = "ENTER ACCOUNT INFORMATION";
        Assert.assertEquals(enterAccountInfoText, expectedText);

        //9. Fill details: Title, Name, Email, Password, Date of birth
        //10. Select checkbox 'Sign up for our newsletter!'
        //11. Select checkbox 'Receive special offers from our partners!'
        //12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
        //13. Click 'Create Account button'
        //14. Verify that 'ACCOUNT CREATED!' is visible

        signupPage.accountTitleRadioButton.click();

        password=faker.internet().password();
        writeToExcel(email, password); // faker ile ürettiğim email ve şifreyi sonraki testlerde kullanacagım için excel file a kaydettim
        System.out.println(email+"/n"+password);

        signupPage.accountPassword.sendKeys(password);

        Select select1 = new Select(signupPage.dateOfBirth_days);
        select1.selectByValue("15");

        Select select2 = new Select(signupPage.dateOfBirth_months);
        select2.selectByIndex(1);

        Select select3 = new Select(signupPage.dateOfBirth_years);
        select3.selectByValue("1983");

        signupPage.checkboxNewsletter.click();
        signupPage.checkboxOptin.click();
        //homePage.addressFirstName.sendKeys(faker.name().firstName());

        Actions actions = new Actions(driver);

        actions.click(signupPage.addressFirstName)
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

        scrollIntoVIewJS(signupPage.continueButton);
        signupPage.continueButton.click();
        signupPage.loggedInText.isDisplayed();

        //17. Click 'Delete Account' button
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button

        clickElementByJS(signupPage.deleteAccountButton);

        Driver.getDriver().navigate().back();
        Driver.getDriver().navigate().refresh();

        homePage.logoutButton.click();

    }

}
