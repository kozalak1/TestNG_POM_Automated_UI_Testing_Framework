package tests;

import org.apache.poi.ss.usermodel.Cell;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import utilities.ConfigReader;
import utilities.ReusableMethods;

import static utilities.ExcelUtil.bringCellValue;
import static utilities.JsUtils.clickElementByJS;
import static utilities.JsUtils.scrollIntoVIewJS;

public class TestCase_02 {

    static HomePage homePage = new HomePage();
    static SignupPage signupPage = new SignupPage();
    static LoginPage loginPage = new LoginPage();


    @Test
    public void positiveLoginTest () throws InterruptedException {

        ReusableMethods.homePageNavigateAndVisible();

//4. Click on 'Signup / Login' button
//5. Verify 'Login to your account' is visible
        clickElementByJS(signupPage.signuploginButton);
        String actualLoginYourAccount = loginPage.loginYourAccount.getText();
        //System.out.println(loginYourAccnt);
        Assert.assertEquals(actualLoginYourAccount, "Login to your account");

//6. Enter correct email address and password

        String path = "src/test/java/resources/testData.xlsx";
        String sayfaAdi = "page_1";
        int satirIndex = 0;
        int hucreIndex = 0;
        // registerUser metodunda faker class ile urettiğimiz ve Excel e kaydettiğimiz email ve password u aşağıdaki metod ile Excel den okuyup giriş yapacaz
        Cell cell1 = bringCellValue(path, sayfaAdi, satirIndex, hucreIndex);
        String email = String.valueOf((cell1));
        System.out.println(email);
        scrollIntoVIewJS(loginPage.loginEmail);
        loginPage.loginEmail.sendKeys(email);

        hucreIndex = 1;
        Cell cell2 = bringCellValue(path, sayfaAdi, satirIndex, hucreIndex);
        String password = String.valueOf((cell2));
        System.out.println(password);
        loginPage.loginPassword.sendKeys(password);

//7. Click 'login' button
        loginPage.loginSubmitButton.click();

//8. Verify that 'Logged in as username' is visible
        String actualUsername = loginPage.loggedUsername.getText();
        System.out.println(actualUsername);
        Assert.assertTrue(actualUsername.contains(ConfigReader.getProperty("userName")));

//9. Click 'Delete Account' button
//10. Verify that 'ACCOUNT DELETED!' is visible

        signupPage.deleteAccountButton.click();

    }

}
