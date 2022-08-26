package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SignupPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;

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



    @Test (dataProvider = "userName")
    public static void registerUser (String userName) throws IOException {

        ReusableMethods.homePageNavigateAndVisible();

        // 4. Click on 'Signup / Login' button
        // 5. Verify 'New User Signup!' is visible
        signupPage.signuploginButton.click();
        signupPage.newUserSignText.isDisplayed();

        //6. Enter name and email address
        //7. Click 'Signup' button
        //8. Verify that 'ENTER ACCOUNT INFORMATION' is visible

        signupPage.nameBox.sendKeys(userName); // userName i dataprovider ile sağlıyoruz.

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
        System.out.println(email+"\n"+password);

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

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(signupPage.continueButton));
        // explicitly wait kullanabilmek icin once wait objesi olusturduk
        // web element zaten gorunur olmadigindan locate etmemiz de mumkun olmayacak
        // bu durumda locate ve bekleme islemini beraber yapmak gerekir
        // driver'ımızı until() metodu ile beklediğimiz / istediğimiz koşul olana kadar bekletiyoruz

        signupPage.continueButton.click();

        //16. Verify that 'Logged in as username' is visible
        signupPage.loggedInText.isDisplayed();

        //17. Click 'Delete Account' button
        //18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button

        clickElementByJS(signupPage.deleteAccountButton);

        Driver.getDriver().navigate().back();
        Driver.getDriver().navigate().refresh();

        homePage.logoutButton.click();

    }
    // dataprovider ile DDT consepti uygulanır, aşağıdaki 3 farklı username kullanılarak test 3 kez çalıştırılır
    @DataProvider(name = "userName")
    public Object [][] user() {
        String [][] user = {{"hakan"}, {"kemal"}, {"can"}};
        return user;
    }

}
