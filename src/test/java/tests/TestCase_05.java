package tests;

import org.apache.poi.ss.usermodel.Cell;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import static utilities.ExcelUtil.bringCellValue;
import static utilities.JsUtils.clickElementByJS;


public class TestCase_05 {

    static SignupPage signupPage = new SignupPage();
    static LoginPage loginPage = new LoginPage();

    @Test
    public void registerExistingEmail(){

        //1. Launch browser
        //2. Navigate to url 'http://automationexercise.com'
        //3. Verify that home page is visible successfully
        ReusableMethods.homePageNavigateAndVisible();

        // 4. Click on 'Signup / Login' button
        // 5. Verify 'New User Signup!' is visible
        //ReusableMethods.fluentWait(signupPage.signuploginButton, 10);
        clickElementByJS(signupPage.signuploginButton);

        signupPage.newUserSignText.isDisplayed();

        //6. Enter name (username) and already registered email address
        signupPage.nameBox.sendKeys(ConfigReader.getProperty("userName"));

        String path = "src/test/java/resources/testData.xlsx";
        String sayfaAdi = "page_1";
        int satirIndex = 0;
        int hucreIndex = 0;
        // registerUser metodunda faker class ile urettiğimiz ve Excel e kaydettiğimiz email adresini aşağıdaki metod ile Excel den okuyup tekrar register yapmaya çalışacaz
        Cell cell1 = bringCellValue(path, sayfaAdi, satirIndex, hucreIndex);
        String email = String.valueOf((cell1));
        signupPage.emailBox.sendKeys(email);

        //7. Click 'Signup' button
        signupPage.signupButton.click();
        //8. Verify error 'Email Address already exist!' is visible
        loginPage.emailExistMsj.isDisplayed();

    }


}
