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

public class TestCase_03_03 {

    // a decision rule of third
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

        //6. Enter incorrect email address and CORRECT PASSWORD
        loginPage.loginEmail.sendKeys(ConfigReader.getProperty("incorrectEmail"));

        String path = "src/test/java/resources/testData.xlsx";
        String sayfaAdi = "page_1";
        int satirIndex = 0;
        int hucreIndex = 1;
        Cell cell2 = bringCellValue(path, sayfaAdi, satirIndex, hucreIndex);
        String password = String.valueOf((cell2));
        System.out.println(password);
        loginPage.loginPassword.sendKeys(password);

        //7. Click 'login' button

        loginPage.loginSubmitButton.click();

        //8. Verify error 'Your email or password is incorrect!' is visible
        homePage.emailAndPasswordIncorrectMsj.isDisplayed();

    }

}
