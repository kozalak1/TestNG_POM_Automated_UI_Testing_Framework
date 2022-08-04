package utilities;

import org.testng.Assert;

public class ReusableMethods {



    // sitenin ana sayfasına gitmek ve ana sayfanın görünür olması sürekli gerekli olacagı için ayrı bir metod haline getirildi

    public static void homePageNavigateAndVisible() {

        // 1. Launch browser
        // 2. Navigate to url 'http://automationexercise.com'

        Driver.getDriver().get(ConfigReader.getProperty("aeUrl"));

        //  3. Verify that home page is visible successfully

        String actualHomePageTitle = Driver.getDriver().getTitle();
        System.out.println(actualHomePageTitle);
        String expectedHomePageTitle = ConfigReader.getProperty("title");
        Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle);

    }
}
